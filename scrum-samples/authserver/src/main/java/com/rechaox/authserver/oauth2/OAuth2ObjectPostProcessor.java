package com.rechaox.authserver.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.web.OAuth2ClientAuthenticationFilter;

import java.nio.charset.StandardCharsets;

/**
 * @author Bing D. Yee
 * @since 2021/09/20
 */
@Slf4j
public class OAuth2ObjectPostProcessor implements ObjectPostProcessor<Object> {

    @Override
    public <O> O postProcess(O object) {
        if (object instanceof OAuth2ClientAuthenticationFilter) {
            OAuth2ClientAuthenticationFilter filter = (OAuth2ClientAuthenticationFilter) object;
            filter.setAuthenticationFailureHandler((request, response, exception) -> {
                OAuth2AuthenticationException oAuth2AuthenticationException = (OAuth2AuthenticationException) exception;
                OAuth2Error oAuth2Error = oAuth2AuthenticationException.getError();
                switch (oAuth2Error.getErrorCode()) {
                    case OAuth2ErrorCodes.INVALID_CLIENT:
                        log.info("未知的客户端");
                        break;
                    case OAuth2ErrorCodes.ACCESS_DENIED:
                        log.info("您无权限访问");
                        break;
                    default:
                        break;
                }
                log.error("错误原因:[{}]", oAuth2Error);
                log.info("认证异常", exception);
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(MediaType.APPLICATION_JSON.toString());
                response.getWriter().write("{\"code\":-2, \"msg\":\"认证失败\"}");
            });
        }
        return object;
    }

}
