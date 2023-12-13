package com.volkov.security.controllers;

import com.volkov.security.dto.AuthDTO;
import com.volkov.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @RequestMapping(value = "/auth/login", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> performLogin(@RequestBody AuthDTO authDTO) {
        return authService.getAuth(authDTO.getUsername(), authDTO.getPassword());
    }
}
