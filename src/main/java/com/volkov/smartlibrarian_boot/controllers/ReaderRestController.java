package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.controllers.api.ReaderRestApi;
import com.volkov.smartlibrarian_boot.dto.ReaderDTO;
import com.volkov.smartlibrarian_boot.services.ReaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class ReaderRestController implements ReaderRestApi {

    private final ReaderService readerService;

    @Override
    public ResponseEntity<List<ReaderDTO>> getAll() {
        var users = readerService.findAllDTOs();
        log.info("Total records from index() method to return: " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
