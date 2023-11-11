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
    public ResponseEntity<List<BookDTO>> getAll(Boolean byYear, Integer pageNumber) {
        List<BookDTO> books;
        if (pageNumber == 0) {
            books = byYear ?
                    bookService.findAllDTOsSortedByYear() :
                    bookService.findAllDTOs();
        } else {
            books = byYear ?
                    bookService.findAllDTOsPerPageSortedByYear(pageNumber) :
                    bookService.findAllDTOsPerPage(pageNumber);
        }
        log.info("Total records from index() method to return: " + books.size());
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<BookDTO> getById(Integer id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<BookDTO> create(BookDTO bookDTO) {
        var savedDto = bookService.saveDto(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @Override
    public ResponseEntity<BookDTO> update(BookDTO bookDTO) {
        return bookService.updateDTO(bookDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        var optionalReader = bookService.deleteDTO(id);
        return optionalReader.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookDTO>> search(String contain) {
        var books = bookService.searchDTOs(contain);
        return books.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> assign(BookDTO bookDTO) {
        var book = bookService.assignDTO(bookDTO);
        return book == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> release(Integer id) {
        var book = bookService.releaseDTO(id);
        return book.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}