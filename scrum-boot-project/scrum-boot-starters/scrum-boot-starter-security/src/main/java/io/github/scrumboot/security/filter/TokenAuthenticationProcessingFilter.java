package io.github.scrumboot.security.filter;


import io.github.scrumboot.langs.Strings;
import io.github.scrumboot.security.AuthorizationTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 令牌信息解析
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class TokenAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(TokenAuthenticationProcessingFilter.class);

    private final AuthorizationTokenManager authorizationTokenManager;

    public TokenAuthenticationProcessingFilter(AuthorizationTokenManager authorizationTokenManager) {
        this.authorizationTokenManager = authorizationTokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final boolean debug = logger.isDebugEnabled();
        try {
            String accessToken = AuthorizationTokenManager.extractAccessToken(request);
            if (Strings.isNotBlank(accessToken)) {
                Authentication authentication = authorizationTokenManager.loadAuthentication(accessToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    if (debug) {
                        logger.debug("Authentication success: " + authentication);
                    }
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            if (debug) {
                logger.debug("Authentication request failed: ", e);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(authorizationTokenManager != null, "AuthorizationTokenManager is required");
    }

}

