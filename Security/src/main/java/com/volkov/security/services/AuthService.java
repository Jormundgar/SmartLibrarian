package com.volkov.security.services;

import com.volkov.security.dto.AuthRequest;
import com.volkov.security.dto.AuthResponse;
import com.volkov.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest authRequest) {
        var accessToken = jwtUtil.generate(authRequest.getUsername(), "ACCESS");
        var refreshToken = jwtUtil.generate(authRequest.getUsername(), "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
}
