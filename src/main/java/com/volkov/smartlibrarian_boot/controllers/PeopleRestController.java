package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Slf4j
public class PeopleRestController {

    private final PersonService personService;

    @RequestMapping(value = "/people/all", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        var users = personService.findAllDTOs();
        log.info("Total records from index() method to return: " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
