package io.github.scrumboot.security.handler;

import io.github.scrumboot.langs.Jsons;
import io.github.scrumboot.langs.model.ApiResponse;
import io.github.scrumboot.langs.model.status.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问限制
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class AccessAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(Jsons.toJson(ApiResponse.of(StatusCode.UN_AUTH)));
    }

}