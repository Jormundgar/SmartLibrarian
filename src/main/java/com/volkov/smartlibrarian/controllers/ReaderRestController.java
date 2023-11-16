package com.volkov.smartlibrarian.controllers;

import com.volkov.smartlibrarian.controllers.api.ReaderRestApi;
import com.volkov.smartlibrarian.dto.ReaderDTO;
import com.volkov.smartlibrarian.dto.RecordsNumberDTO;
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
    public ResponseEntity<List<ReaderDTO>> getAll(Boolean byYear, Integer pageNumber) {
        List<ReaderDTO> readers;
        if (pageNumber == 0) {
            readers = byYear ?
                    readerService.findAllDTOsSortedByYear() :
                    readerService.findAllDTOs();
        } else {
            readers = byYear ?
                    readerService.findAllDTOsPerPageSortedByYear(pageNumber) :
                    readerService.findAllDTOsPerPage(pageNumber);
        }
        log.info("Total records from index() method to return: " + readers.size());
        log.info("pageNumber: " + pageNumber);
        log.info("byYear: " + byYear);
        return readers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(readers);
    }

    @Override
    public ResponseEntity<ReaderDTO> getById(Integer id) {
        return readerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<RecordsNumberDTO> getNumberOfRecords() {
        var readersCountDto = readerService.getReadersCountDto();
        log.info("Total records of readers from DB: " + readersCountDto.getNumberOfRecords());
        return readersCountDto.getNumberOfRecords() == 0 ? ResponseEntity.notFound().build() : ResponseEntity.ok(readersCountDto);
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
    public ResponseEntity<Void> delete(Integer id) {
        var optionalReader = readerService.deleteDTO(id);
        return optionalReader.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}
