package io.github.scrumboot.security.properties;

import io.github.scrumboot.security.social.ClientRegistration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 三方账号配置
 *
 * @author bingdyee
 * @since 2022/03/23
 */
@Configuration
@ConfigurationProperties(prefix = "scrum.security.client")
public class SocialClientProperties {

    private Map<String, ClientRegistration> registration;

    public Map<String, ClientRegistration> getRegistration() {
        return registration;
    }

    public void setRegistration(Map<String, ClientRegistration> registration) {
        this.registration = registration;
    }

}
