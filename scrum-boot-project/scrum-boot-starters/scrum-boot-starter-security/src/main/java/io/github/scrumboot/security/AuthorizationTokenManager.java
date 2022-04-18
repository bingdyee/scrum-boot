package io.github.scrumboot.security;

import io.github.scrumboot.langs.Strings;
import io.github.scrumboot.security.store.TokenStore;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 令牌管理
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class AuthorizationTokenManager {

    private final TokenStore tokenStore;
    private final int expiresIn;

    public AuthorizationTokenManager(TokenStore tokenStore, int expiresIn) {
        this.tokenStore = tokenStore;
        this.expiresIn = expiresIn;
    }

    public AccessToken createAccessToken(Authentication authentication) {
        AccessToken token = new AccessToken(UUID.randomUUID().toString())
                .setRefreshToken(UUID.randomUUID().toString())
                .setExpiresIn(expiresIn * 1000);
        tokenStore.storeAccessToken(token, authentication);
        return token;
    }

    public AccessToken createAccessToken(String accessToken, Authentication authentication) {
        AccessToken token = new AccessToken(accessToken)
                .setRefreshToken(UUID.randomUUID().toString())
                .setExpiresIn(expiresIn * 1000);
        tokenStore.storeAccessToken(token, authentication);
        return token;
    }

    public AccessToken refreshAccessToken(String refreshTokenValue) {
        Authentication authentication = tokenStore.readAuthenticationUsingRefreshToken(refreshTokenValue);
        tokenStore.removeAccessTokenUsingRefreshToken(refreshTokenValue);
        return createAccessToken(authentication);
    }

    public Authentication loadAuthentication(String accessTokenValue) {
        return tokenStore.readAuthentication(accessTokenValue);
    }

    public void removeAccessToken(String accessToken) {
        tokenStore.removeAccessToken(accessToken);
    }

    public static String extractAccessToken(HttpServletRequest request) {
        String accessTokenValue = request.getParameter(AccessToken.ACCESS_TOKEN);
        if (Strings.isBlank(accessTokenValue)) {
            String value = request.getHeader("Authorization");
            if (Strings.isNotBlank(value) && value.toLowerCase().startsWith(AccessToken.BEARER_TYPE.toLowerCase())) {
                accessTokenValue = value.substring(AccessToken.BEARER_TYPE.length()).trim();
            }
        }
        return accessTokenValue;
    }

}
