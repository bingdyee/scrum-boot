package io.github.scrumboot.security.handler;

import io.github.scrumboot.langs.Jsons;
import io.github.scrumboot.langs.model.ApiResponse;
import io.github.scrumboot.security.AccessToken;
import io.github.scrumboot.security.AuthorizationTokenManager;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理
 *
 * @author bingdyee
 * @since 2022/04/01
 */
public abstract class AbstractLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthorizationTokenManager authorizationTokenManager;

    public AbstractLoginSuccessHandler(AuthorizationTokenManager authorizationTokenManager) {
        this.authorizationTokenManager = authorizationTokenManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        preAuthenticationSuccess(request, authentication);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AccessToken accessToken = authorizationTokenManager.createAccessToken(authentication);
        response.getWriter().write(Jsons.toJson(ApiResponse.ok(accessToken)));
    }

    /**
     * 登录成功处理
     *
     * @param request request
     * @param authentication authentication
     */
    public abstract void preAuthenticationSuccess(HttpServletRequest request, Authentication authentication);

}
