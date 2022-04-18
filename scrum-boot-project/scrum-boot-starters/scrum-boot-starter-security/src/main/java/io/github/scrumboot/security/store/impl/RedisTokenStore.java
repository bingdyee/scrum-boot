package io.github.scrumboot.security.store.impl;

import io.github.scrumboot.langs.exception.InvalidTokenException;
import io.github.scrumboot.langs.model.status.StatusCode;
import io.github.scrumboot.security.AccessToken;
import io.github.scrumboot.security.store.TokenStore;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;

/**
 * redis令牌存储
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class RedisTokenStore implements TokenStore {

    private static final JdkSerializationRedisSerializer OBJECT_SERIALIZER = new JdkSerializationRedisSerializer();
    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    private static final String ACCESS_AUTH = "access_auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";

    private final RedisConnectionFactory connectionFactory;

    public RedisTokenStore(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Authentication readAuthentication(String accessTokenValue) {
        byte[] bytes;
        try(RedisConnection conn = connectionFactory.getConnection()) {
            bytes = conn.get(serializeString(ACCESS_AUTH + accessTokenValue));
        }
        if (bytes == null) {
            throw new InvalidTokenException("access_token不存在");
        }
        return (Authentication) deserializeObject(bytes);
    }

    @Override
    public Authentication readAuthenticationUsingRefreshToken(String refreshTokenValue) {
        byte[] bytes;
        try(RedisConnection conn = connectionFactory.getConnection()) {
            bytes = conn.get(serializeString(REFRESH_AUTH + refreshTokenValue));
        }
        if (bytes == null) {
            throw new InvalidTokenException(StatusCode.BAD_REFRESH_TOKEN);
        }
        return (Authentication) deserializeObject(bytes);
    }

    @Override
    public void storeAccessToken(AccessToken accessToken, Authentication authenticationToken) {
        // 删除旧的令牌信息
        removeAccessTokenByUsername(getUname2AccessKey(authenticationToken));
        // 插入新的
        byte[] accessAuthKey = serializeString(ACCESS_AUTH + accessToken.getAccessToken());
        byte[] refreshAuthKey = serializeString(REFRESH_AUTH + accessToken.getRefreshToken());
        byte[] access2RefreshKey = serializeString(ACCESS_TO_REFRESH + accessToken.getAccessToken());
        byte[] refresh2AccessKey = serializeString(REFRESH_TO_ACCESS + accessToken.getRefreshToken());
        byte[] uname2AccessKey = getUname2AccessKey(authenticationToken);

        byte[] access = serializeString(accessToken.getAccessToken());
        byte[] refresh= serializeString(accessToken.getRefreshToken());
        byte[] serializedAuth = serializeObject(authenticationToken);

        try(RedisConnection conn = connectionFactory.getConnection()) {
            conn.openPipeline();
            conn.set(accessAuthKey, serializedAuth);
            conn.set(refreshAuthKey, serializedAuth);
            conn.set(access2RefreshKey, refresh);
            conn.set(refresh2AccessKey, access);
            conn.set(uname2AccessKey, access);
            Date expiration = accessToken.getExpiration();
            if (expiration != null) {
                int seconds = Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                        .intValue();
                conn.expire(accessAuthKey, seconds);
                conn.expire(refreshAuthKey, seconds * 2L);
                conn.expire(access2RefreshKey, seconds);
                conn.expire(refresh2AccessKey, seconds * 2L);
                conn.expire(uname2AccessKey, seconds);
            }
            conn.closePipeline();
        }
    }

    @Override
    public void removeAccessToken(String key) {
        byte[] accessAuthKey = serializeString(ACCESS_AUTH + key);
        byte[] access2RefreshKey = serializeString(ACCESS_TO_REFRESH + key);

        try(RedisConnection conn = connectionFactory.getConnection()) {
            conn.openPipeline();
            conn.get(accessAuthKey);
            conn.get(access2RefreshKey);
            conn.del(accessAuthKey);
            conn.del(access2RefreshKey);
            List<Object> results = conn.closePipeline();
            byte[] auth = (byte[]) results.get(0);
            byte[] refresh = (byte[]) results.get(1);
            Authentication authentication = (Authentication) deserializeObject(auth);
            if (authentication != null) {
                byte[] unameKey = getUname2AccessKey(authentication);
                conn.del(unameKey);
            }
            String refreshValue = deserializeString(refresh);
            conn.del(serializeString(REFRESH_TO_ACCESS + refreshValue));
            conn.del(serializeString(REFRESH_AUTH + refreshValue));
            conn.closePipeline();
        }
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(String refreshToken) {
        byte[] refresh2AccessKey = serializeString(REFRESH_TO_ACCESS + refreshToken);
        try (RedisConnection conn = connectionFactory.getConnection()) {
            byte[] access = conn.get(refresh2AccessKey);
            if (access != null) {
                removeAccessToken(deserializeString(access));
            } else {
                conn.del(serializeString(REFRESH_TO_ACCESS + refreshToken));
                conn.del(serializeString(REFRESH_AUTH + refreshToken));
                conn.closePipeline();
            }
        }
    }

    private void removeAccessTokenByUsername(byte[] uname2AccessKey) {
        byte[] bytes;
        try(RedisConnection conn = connectionFactory.getConnection()) {
            bytes = conn.get(uname2AccessKey);
        }
        if (bytes != null) {
            String accessToken = deserializeString(bytes);
            removeAccessToken(accessToken);
        }
    }

    private byte[] serializeString(String str) {
        return STRING_SERIALIZER.serialize(str);
    }

    private String deserializeString(byte[] bytes) {
        return STRING_SERIALIZER.deserialize(bytes);
    }

    private byte[] serializeObject(Object obj) {
        return OBJECT_SERIALIZER.serialize(obj);
    }

    private Object deserializeObject(byte[] bytes) {
        return OBJECT_SERIALIZER.deserialize(bytes);
    }

    private byte[] getUname2AccessKey(Authentication authentication) {
        return serializeString(UNAME_TO_ACCESS + "app:" +authentication.getName());
    }

}
