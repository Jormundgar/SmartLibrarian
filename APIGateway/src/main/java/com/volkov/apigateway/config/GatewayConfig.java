package com.volkov.apigateway.config;

import com.volkov.apigateway.filters.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthFilter filter;
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend", r -> r.path("/api/**")
                        .filters(f -> f.filter(filter))
//                        .uri("http://localhost:8070/api/"))
                        .uri("lb://backend"))
                .route("security", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
//                        .uri("http://localhost:8060/auth/"))
                        .uri("lb://security"))
                .build();
    }
}
