package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.controllers.api.BookRestApi;
import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.mapper.BookMapper;
import com.volkov.smartlibrarian_boot.services.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    private final BookService bookService;

    @RequestMapping(value = "/books/all", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(BookMapper.INSTANCE.bookListToBookDtoList(bookService.findAll()),
                HttpStatus.OK);
    }
}
