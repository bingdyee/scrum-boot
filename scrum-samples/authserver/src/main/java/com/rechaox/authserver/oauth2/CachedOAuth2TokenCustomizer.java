package com.rechaox.authserver.oauth2;

import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;

/**
 * @author Bing D. Yee
 * @since 2021/09/20
 */
public class CachedOAuth2TokenCustomizer implements OAuth2TokenCustomizer<CachedOAuth2TokenContext> {

    @Override
    public void customize(CachedOAuth2TokenContext context) {

    }

}
