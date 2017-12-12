package sp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.host;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.method;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.path;

@Configuration
@SpringBootApplication
public class SpGateway {

    @Bean
    public RouteLocator customRouteLocator() {
        return Routes.locator()
                .route("organizer.query")
                    .uri("http://localhost:8081")
                    .predicate(host("**localhost**")
//                            .and(path("**/competition/**"))
//                            .and(method("GET")))
                    )
                    .and()
//                .route("organiezr.command")
//                    .uri("http://localhost:8082")
//                    .predicate(host("**localhost**")
//                            .and(path("**/competition/**"))
//                            .and(method("POST"))
//                    )
//                    .and()
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpGateway.class, args);
    }
}
