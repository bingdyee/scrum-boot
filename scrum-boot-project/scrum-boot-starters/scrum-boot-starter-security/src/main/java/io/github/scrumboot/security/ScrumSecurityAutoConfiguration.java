package io.github.scrumboot.security;

import io.github.scrumboot.security.properties.SecurityProperties;
import io.github.scrumboot.security.properties.SocialClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bing D. Yee
 * @since 2022/04/18
 */
@Configuration
public class ScrumSecurityAutoConfiguration {

    @Bean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    @ConditionalOnProperty("scrum.security.client")
    public SocialClientProperties socialClientProperties() {
        return new SocialClientProperties();
    }

}
