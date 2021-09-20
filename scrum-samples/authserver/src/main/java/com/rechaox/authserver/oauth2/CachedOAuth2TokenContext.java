package com.rechaox.authserver.oauth2;

import org.springframework.security.oauth2.server.authorization.OAuth2TokenContext;

/**
 * @author Bing D. Yee
 * @since 2021/09/20
 */
public class CachedOAuth2TokenContext implements OAuth2TokenContext {

    @Override
    public <V> V get(Object key) {
        return null;
    }

    @Override
    public boolean hasKey(Object key) {
        return false;
    }

}
