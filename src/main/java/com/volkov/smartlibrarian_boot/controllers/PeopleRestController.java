package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PeopleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleRestController.class);

    private final PersonService personService;

    @RequestMapping(value = "/people", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }
}
