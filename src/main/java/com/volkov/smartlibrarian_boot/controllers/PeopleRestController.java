package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.mapper.BookMapper;
import com.volkov.smartlibrarian_boot.mapper.PeopleMapper;
import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class PeopleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleRestController.class);

    private final PersonService personService;

    @RequestMapping(value = "/people/all", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> index() {
        var users = personService.findAll();
        LOGGER.info("Total records from index() method to return: " + users.size());
        return new ResponseEntity<>(PeopleMapper.INSTANCE.personListToPersonDTOList(users), HttpStatus.OK);
    }
}
