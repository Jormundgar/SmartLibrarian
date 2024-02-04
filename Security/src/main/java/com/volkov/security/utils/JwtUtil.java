package com.volkov.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expirationTime;
    private Key key;

    @PostConstruct
    public void init() {
        this.key=Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final var expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generate(UserDetails userDetails, String type) {
        Map<String, Object> claims = new HashMap<>();
        var username = userDetails.getUsername();
        claims.put("username", username);
        claims.put("type", type);
        return doGenerateToken(claims, username, type);
    }

    private String doGenerateToken(Map<String, Object> claims, String username, String type) {
        long expirationTimeLong;
        if ("ACCESS".equals(type)) {
            expirationTimeLong = Long.parseLong(expirationTime) * 1000;
        } else {
            expirationTimeLong = Long.parseLong(expirationTime) * 1000 * 5;
        }
        final var createDate = new Date();
        final var expirationDate = new Date(createDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
