package com.volkov.smartlibrarian.controllers;

import com.volkov.smartlibrarian.controllers.api.ReaderRestApi;
import com.volkov.smartlibrarian.dto.ReaderDTO;
import com.volkov.smartlibrarian.services.ReaderService;
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
        var readers = readerService.findAllDTOs();
        log.info("Total records from index() method to return: " + readers.size());
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }
}
