package com.volkov.apigateway.filters;

import com.volkov.apigateway.util.AuthUtil;
import com.volkov.apigateway.util.JWTUtil;
import com.volkov.apigateway.validator.RouteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RefreshScope
@RequiredArgsConstructor
public class AuthFilter implements GatewayFilter {

    private final RouteValidator routeValidator;
    private final JWTUtil jwtUtil;
    private final AuthUtil authUtil;

    @Value("${authentication.enabled}")
    private boolean authEnabled;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }


}
