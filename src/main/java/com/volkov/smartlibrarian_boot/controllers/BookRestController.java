package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.mapper.BookMapper;
import com.volkov.smartlibrarian_boot.services.BookService;
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
public class BookRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    private final BookService bookService;

    @RequestMapping(value = "/books/all", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> index() {
        var books = bookService.findAll();
        LOGGER.info("Total records from index() method to return: " + books.size());
        return new ResponseEntity<>(BookMapper.INSTANCE.bookListToBookDtoList(books), HttpStatus.OK);
    }
}
