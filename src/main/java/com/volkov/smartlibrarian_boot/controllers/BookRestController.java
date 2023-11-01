package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.mapper.BookMapper;
import com.volkov.smartlibrarian_boot.services.BookService;
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
public class BookRestController {

    private final BookService bookService;

    @RequestMapping(value = "/books/all", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        var books = bookService.findAll();
        log.info("Total records from index() method to return: " + books.size());
        return new ResponseEntity<>(BookMapper.INSTANCE.bookListToBookDtoList(books), HttpStatus.OK);
    }
}
