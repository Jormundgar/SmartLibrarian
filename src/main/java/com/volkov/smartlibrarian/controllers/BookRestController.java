package com.volkov.smartlibrarian.controllers;

import com.volkov.smartlibrarian.controllers.api.BookRestApi;
import com.volkov.smartlibrarian.dto.BookDTO;
import com.volkov.smartlibrarian.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class BookRestController implements BookRestApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDTO>> getAll() {
        var books = bookService.findAllDTOs();
        log.info("Total records from index() method to return: " + books.size());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}