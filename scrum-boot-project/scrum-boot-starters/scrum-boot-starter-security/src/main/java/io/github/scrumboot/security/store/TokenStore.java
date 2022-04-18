package io.github.scrumboot.security.store;

import io.github.scrumboot.security.AccessToken;
import org.springframework.security.core.Authentication;

/**
 * 令牌存储接口
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public interface TokenStore {

    /**
     *  通过令牌获取权限信息
     *
     * @param accessTokenValue 令牌
     * @return Authentication 权限信息
     */
    Authentication readAuthentication(String accessTokenValue);

    /**
     * 通过刷新令牌获取权限信息
     *
     * @param refreshTokenValue refresh_token 刷新令牌
     * @return Authentication 权限信息
     */
    Authentication readAuthenticationUsingRefreshToken(String refreshTokenValue);


    /**
     *  保存令牌
     *
     * @param accessToken AccessToken 令牌实体
     * @param authenticationToken Authentication 权限信息
     */
    void storeAccessToken(AccessToken accessToken, Authentication authenticationToken);

    /**
     * 通过令牌删除权限信息
     *
     * @param key access_token 令牌
     */
    void removeAccessToken(String key);


    /**
     *  通过刷新令牌删除权限信息
     *
     * @param refreshToken refresh_token 刷新令牌
     */
    void removeAccessTokenUsingRefreshToken(String refreshToken);

}
