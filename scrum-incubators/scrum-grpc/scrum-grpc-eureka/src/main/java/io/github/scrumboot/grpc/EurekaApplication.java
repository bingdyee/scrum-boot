package io.github.scrumboot.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Bing D. Yee
 * @since 2022/05/21
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}
