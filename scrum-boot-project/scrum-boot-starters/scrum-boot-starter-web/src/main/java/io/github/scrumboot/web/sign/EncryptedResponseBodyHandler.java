package io.github.scrumboot.web.sign;

import io.github.scrumboot.langs.Jsons;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.UUID;

/**
 * 统一接口返回数据结构
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
@RestControllerAdvice("com.rechaox.epiphany")
public class EncryptedResponseBodyHandler implements ResponseBodyAdvice<Object> {

    private final HttpMessageEncryptor httpMessageEncryptor;

    public EncryptedResponseBodyHandler(HttpMessageEncryptor httpMessageEncryptor) {
        this.httpMessageEncryptor = httpMessageEncryptor;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String plain = httpMessageEncryptor.encode(Jsons.toJson(body));
        return EncryptedBody.of(UUID.randomUUID().toString().replace("-", ""), plain);
    }

}
