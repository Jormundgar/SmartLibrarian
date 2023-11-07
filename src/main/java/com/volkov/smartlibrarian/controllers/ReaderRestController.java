package com.volkov.smartlibrarian.controllers;

import com.volkov.smartlibrarian.controllers.api.ReaderRestApi;
import com.volkov.smartlibrarian.dto.ReaderDTO;
import com.volkov.smartlibrarian.models.Reader;
import com.volkov.smartlibrarian.services.ReaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@Slf4j
public class ReaderRestController implements ReaderRestApi {

    private final ReaderService readerService;

    @Override
    public ResponseEntity<List<ReaderDTO>> getAll() {
        var readers = readerService.findAllDTOs();
        log.info("Total records from ReaderRestController.getAll() method to return: " + readers.size());
        return readers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(readers);
    }

    @Override
    public ResponseEntity<ReaderDTO> getById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<ReaderDTO> create(ReaderDTO readerDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ReaderDTO> update(ReaderDTO readerDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        var optionalReader = readerService.deleteDTO(id);
        return optionalReader.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }
}
