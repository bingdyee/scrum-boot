package io.github.scrumboot.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * explorer: http://127.0.0.1:8080/graphiql
 *
 * @author Bing D. Yee
 * @since 2022/08/24
 */
@SpringBootApplication
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }

}
