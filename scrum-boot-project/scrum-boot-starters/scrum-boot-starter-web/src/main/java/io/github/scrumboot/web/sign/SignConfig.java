package io.github.scrumboot.web.sign;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author bingdyee
 * @since 2022/04/01
 */
@Configuration
public class SignConfig {

    @Bean
    public FilterRegistrationBean<Filter> userSecurityFilterRegistration() {
        EncryptedRequestFilter filter = new EncryptedRequestFilter(httpMessageEncryptor());
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(filter);
        registration.addUrlPatterns("/v1/*");
        return registration;
    }

    @Bean
    public HttpMessageEncryptor httpMessageEncryptor() {
        return new Base64HttpMessageEncryptor();
    }

}
