package io.github.scrumboot.web.sign;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author bingdyee
 * @since 2022/04/01
 */
public class EncryptedRequestFilter implements Filter {

    private final HttpMessageEncryptor httpMessageEncryptor;

    public EncryptedRequestFilter(HttpMessageEncryptor httpMessageEncryptor) {
        this.httpMessageEncryptor = httpMessageEncryptor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        byte[] body = request.getInputStream().readAllBytes();
        chain.doFilter(new EncryptedRequestWrapper(servletRequest, body, httpMessageEncryptor), response);
    }

}
