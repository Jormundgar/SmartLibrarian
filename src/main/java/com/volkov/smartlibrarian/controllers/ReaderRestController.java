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
        log.info("Total records from ReaderRestController.getAll() method to return: " + readers.size());
        return readers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(readers);
    }

    @Override
    public ResponseEntity<ReaderDTO> getById(Integer id) {
        return readerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ReaderDTO> create(ReaderDTO readerDTO) {
        var savedDto = readerService.saveDto(readerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @Override
    public ResponseEntity<ReaderDTO> update(ReaderDTO readerDTO) {
        return readerService.updateDTO(readerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(ReaderDTO readerDTO) {
        var optionalReader = readerService.deleteDTO(readerDTO);
        return optionalReader.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}
