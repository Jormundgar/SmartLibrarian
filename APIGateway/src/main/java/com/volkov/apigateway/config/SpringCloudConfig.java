package com.volkov.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend", r -> r.path("/api/**")
                        .uri("http://localhost:8070/api/"))
                .route("frontend", r -> r.path("/**")
                        .uri("http://localhost:8080/"))
                .route("security", r -> r.path("/auth/**")
                        .uri("http://localhost:8060/auth/"))
                .build();
    }
}
