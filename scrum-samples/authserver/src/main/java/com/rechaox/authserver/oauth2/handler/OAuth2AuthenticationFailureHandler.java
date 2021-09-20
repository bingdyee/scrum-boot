package com.rechaox.authserver.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bing D. Yee
 * @since 2021/09/20
 */
@Slf4j
public class OAuth2AuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        OAuth2AuthenticationException oAuth2AuthenticationException = (OAuth2AuthenticationException) exception;
        OAuth2Error error = oAuth2AuthenticationException.getError();
        log.error("错误原因:[{}]", error);
        log.info("认证异常", exception);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write("{\"code\":-1, \"message\":\"认证失败\"}");
    }

}
