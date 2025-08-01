package com.tvm.Config;

import com.tvm.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig
{
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**")  // no filter
                        .uri("http://localhost:8081"))

                .route("product-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8084"))

                .route("vendor-service", r -> r.path("/vendor/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8083"))

                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8082"))

                .route("payment-service", r -> r.path("/payment/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8088"))

                .route("notification-service", r ->r.path("/notification/**")
                        .filters(f ->f.filter(authFilter))
                        .uri("http://localhost:8085"))

                .route("order-service", r ->r.path("/order/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8085"))

                .route("schedular-service", r ->r.path("/schedular/**")
                        .filters(f ->f.filter(authFilter))
                        .uri("http://localhost:8087"))
                .build();

    }
}
