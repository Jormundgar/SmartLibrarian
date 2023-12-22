package com.volkov.security.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


//@Service
//@AllArgsConstructor
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//
//    public ResponseEntity<?> getAuth(String username, String password) {
//        var auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        return ResponseEntity.ok().build();
//    }
//}
