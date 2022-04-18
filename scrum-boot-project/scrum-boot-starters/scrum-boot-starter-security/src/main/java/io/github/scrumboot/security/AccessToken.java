package io.github.scrumboot.security;

import java.util.*;

/**
 * description:
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class AccessToken extends HashMap<String, Object> {

    private static final long serialVersionUID = 8293112687332098569L;

    public static final String BEARER_TYPE = "Bearer";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String TOKEN_TYPE = "token_type";
    public static final String EXPIRES_IN = "expires_in";
    public static final String EMPTY = "";

    private Date expiration;
    private Map<String, Object> additionalInformation = Collections.emptyMap();

    public AccessToken(String value) {
        put(ACCESS_TOKEN, value);
        put(TOKEN_TYPE, BEARER_TYPE.toLowerCase());
    }

    public AccessToken setAccessToken(String accessTokenValue) {
        this.put(ACCESS_TOKEN, accessTokenValue);
        return this;
    }

    public String getAccessToken() {
        return get(ACCESS_TOKEN) != null ? (String) this.get(ACCESS_TOKEN) : EMPTY;
    }

    public AccessToken setRefreshToken(String refreshTokenValue) {
        this.put(REFRESH_TOKEN, refreshTokenValue);
        return this;
    }

    public String getRefreshToken() {
        return get(REFRESH_TOKEN) != null ? (String) this.get(REFRESH_TOKEN) : EMPTY;
    }

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }

    protected AccessToken setExpiresIn(int delta) {
        this.expiration = new Date(System.currentTimeMillis() + delta);
        put(EXPIRES_IN, getExpiresIn());
        return this;
    }

    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }

    public String getTokenType() {
        return get(TOKEN_TYPE) != null ? (String) get(TOKEN_TYPE) : EMPTY;
    }

    public AccessToken setTokenType(String tokenType) {
        put(TOKEN_TYPE, tokenType);
        return this;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public AccessToken setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = new LinkedHashMap<>(additionalInformation);
        putAll(additionalInformation);
        return this;
    }

    public Date getExpiration() {
        return expiration;
    }

}
