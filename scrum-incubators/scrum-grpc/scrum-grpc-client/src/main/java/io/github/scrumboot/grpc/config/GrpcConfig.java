package io.github.scrumboot.grpc.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bing D. Yee
 * @since 2022/05/19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "grpc.client")
public class GrpcConfig {

    private String host;

    private Integer port;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }

}
