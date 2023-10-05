package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.controllers.api.BookRestApi;
import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController implements BookRestApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDTO>> getAll() {
        return null;
//        var books = bookService.findAllDto();
//        if (books.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(bookService.findAllDto().stream().toList());
//        }
    }

    @Override
    public ResponseEntity<BookDTO> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<BookDTO> create(BookDTO bookDTO) {
        return null;
    }

    @Override
    public ResponseEntity<BookDTO> update(Integer id, BookDTO bookDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }
}
