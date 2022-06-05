package io.github.scrumboot.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * The UserDetails configuration.
 *
 * @author felord.cn
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
public class UserDetailsConfig {

    /**
     *
     * @return the user details service
     */
    @Bean
    UserDetailsService notFoundUserDetailsService() {
        return username -> {
            throw new UsernameNotFoundException("用户不存在");
        };
    }

}
