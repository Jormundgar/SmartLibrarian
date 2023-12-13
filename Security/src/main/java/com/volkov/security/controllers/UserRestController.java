package com.volkov.security.controllers;

import com.volkov.security.controllers.api.UserRestApi;
import com.volkov.security.dto.UserDTO;
import com.volkov.security.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class UserRestController implements UserRestApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> create(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }
}
