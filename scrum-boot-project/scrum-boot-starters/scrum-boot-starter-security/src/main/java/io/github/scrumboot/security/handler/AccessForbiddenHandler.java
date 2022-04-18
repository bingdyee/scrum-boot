package io.github.scrumboot.security.handler;

import io.github.scrumboot.langs.Jsons;
import io.github.scrumboot.langs.model.ApiResponse;
import io.github.scrumboot.langs.model.status.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拒绝访问处理器
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class AccessForbiddenHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String body = Jsons.toJson(ApiResponse.of(StatusCode.FORBIDDEN));
        response.getWriter().write(body);
    }

}
