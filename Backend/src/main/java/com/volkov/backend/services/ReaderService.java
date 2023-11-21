package com.volkov.backend.services;

import com.volkov.backend.dto.ReaderDTO;
import com.volkov.backend.dto.RecordsNumberDTO;
import com.volkov.backend.mapper.ReaderMapper;
import com.volkov.backend.models.Reader;
import com.volkov.backend.repositories.ReadersRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ReaderService {

    private final ReadersRepository readersRepository;
    private final ReaderMapper readerMapper;

    public List<ReaderDTO> findAllDTOs() {
        var allByOrderById = readersRepository.findAllByOrderById();
        return allByOrderById.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<ReaderDTO> findAllDTOsPerPage(int numberPage) {
        var allPerPage = readersRepository.findAllByOrderById(PageRequest.of(numberPage - 1, 5));
        return allPerPage.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<ReaderDTO> findAllDTOsSortedByYear() {
        var allSortedByYear = checkIfExpired(readersRepository.findAll(Sort.by("yearOfBirth")));
        return allSortedByYear.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<ReaderDTO> findAllDTOsPerPageSortedByYear(int numberPage) {
        var allPerPageSortedByYear = checkIfExpired(readersRepository.
                findAll(PageRequest.of(numberPage - 1, 5,
                Sort.by("yearOfBirth"))).getContent());
        return allPerPageSortedByYear.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    private List<Reader> checkIfExpired(List<Reader> readers) {
        readers.forEach(reader -> reader.getReaderBooks().forEach(book -> {
            var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
            long bookedFor = 864000000;
            book.setExpired(check > bookedFor);
        }));
        return readers;
    }

    public Optional<ReaderDTO> findById(Integer id) {
        var reader = readersRepository.findById(id);
        return reader.map(readerMapper::readerToReaderDTO);
    }

    @Transactional
    public ReaderDTO saveDto(ReaderDTO readerDTO) {
        var reader = readerMapper.readerDTOToReader(readerDTO);
        var savedReader = readersRepository.save(reader);
        return readerMapper.readerToReaderDTO(savedReader);
    }

    @Transactional
    public Optional<ReaderDTO> updateDTO(ReaderDTO readerDTO) {
        var readerFromDB = readersRepository.findById(readerDTO.getId());
        var newReader = readerMapper.readerDTOToReader(readerDTO);
        Reader updatedReader;
        if (readerFromDB.isEmpty()) {
            return Optional.empty();
        } else {
            updatedReader = readerFromDB.get();
        }
        updatedReader.setName(newReader.getName());
        updatedReader.setYearOfBirth(newReader.getYearOfBirth());
        readersRepository.save(updatedReader);
        return Optional.of(readerMapper.readerToReaderDTO(updatedReader));
    }

    @Transactional
    public Optional<Reader> deleteDTO(Integer id) {
        var optionalSavedReader = readersRepository.findById(id);
        if (optionalSavedReader.isPresent()) {
            readersRepository.deleteById(id);
        }
        return optionalSavedReader;
    }

    public RecordsNumberDTO getReadersCountDto() {
        var count = readersRepository.count();
            return new RecordsNumberDTO(count);
    }
}
