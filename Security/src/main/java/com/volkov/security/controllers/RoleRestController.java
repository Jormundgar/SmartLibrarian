package com.volkov.security.controllers;

import com.volkov.security.controllers.api.RoleRestApi;
import com.volkov.security.dto.RoleDTO;
import com.volkov.security.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class RoleRestController implements RoleRestApi {

    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleDTO> create(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<RoleDTO> update(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }
}
