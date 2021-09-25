package com.rechaox.scrum.log;

import com.rechaox.scrum.log.annotation.EnableOperateLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@EnableOperateLog(tenant = "com")
@SpringBootApplication
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
