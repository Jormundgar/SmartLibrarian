package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.controllers.api.PeopleRestApi;
import com.volkov.smartlibrarian_boot.dto.PersonDTO;
import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PeopleRestController implements PeopleRestApi {

    private final PersonService personService;

    @Override
    public ResponseEntity<List<PersonDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<PersonDTO> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<PersonDTO> create(PersonDTO personDTO) {
        return null;
    }

    @Override
    public ResponseEntity<PersonDTO> update(Integer id, PersonDTO personDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }
}
