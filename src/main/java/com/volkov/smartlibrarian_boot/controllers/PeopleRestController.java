package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.controllers.api.PeopleRestApi;
import com.volkov.smartlibrarian_boot.dto.PersonDTO;
import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class PeopleRestController implements PeopleRestApi {

    private final PersonService personService;

    @Override
    public ResponseEntity<List<PersonDTO>> getAll() {
        var users = personService.findAllDTOs();
        log.info("Total records from index() method to return: " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
