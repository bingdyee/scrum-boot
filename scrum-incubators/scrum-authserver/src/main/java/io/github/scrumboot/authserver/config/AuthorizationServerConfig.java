package io.github.scrumboot.authserver.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import io.github.scrumboot.authserver.utils.Jwks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Bing D. Yee
 * @since 2022/05/30
 */
@Configuration
public class AuthorizationServerConfig {

    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    @Bean("authorizationServerSecurityFilterChain")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer<>();
        //  把自定义的授权确认URI加入配置
        authorizationServerConfigurer.authorizationEndpoint(authorizationEndpointConfigurer ->
                authorizationEndpointConfigurer.consentPage(CUSTOM_CONSENT_PAGE_URI));

        RequestMatcher authorizationServerEndpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

        // 拦截 授权服务器相关的请求端点
        http.requestMatcher(authorizationServerEndpointsMatcher)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                // 忽略掉相关端点的csrf
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(authorizationServerEndpointsMatcher))
                .formLogin()
                .and()
                // 应用 授权服务器的配置
                .apply(authorizationServerConfigurer)
                .and()
                .oauth2ResourceServer().jwt();
        return http.build();
    }

    /**
     * 配置 OAuth2.0 provider元信息
     *
     * @param port the port
     * @return the provider settings
     * @since 1.0.0
     */
    @Bean
    public ProviderSettings providerSettings(@Value("${server.ip:localhost}") String ip, @Value("${server.port}") Integer port) {
        return ProviderSettings.builder().issuer("http://" + ip + ":" + port).build();
    }



}
