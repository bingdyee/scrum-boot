package io.github.scrumboot.security.handler;

import io.github.scrumboot.langs.Jsons;
import io.github.scrumboot.langs.model.ApiResponse;
import io.github.scrumboot.langs.model.status.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String body = Jsons.toJson(ApiResponse.of(StatusCode.LOGIN_FAILED.getCode(), exception.getMessage()));
        response.getWriter().write(body);
    }

}
