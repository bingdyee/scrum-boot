package com.rechaox.incubators.workflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Bing D. Yee
 * @since 2021/12/05
 */
@Configuration
public class WorkflowConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return null;
    }

}
