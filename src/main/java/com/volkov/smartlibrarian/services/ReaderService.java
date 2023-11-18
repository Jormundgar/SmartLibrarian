package com.volkov.smartlibrarian.services;

import com.volkov.smartlibrarian.dto.ReaderDTO;
import com.volkov.smartlibrarian.dto.RecordsNumberDTO;
import com.volkov.smartlibrarian.mapper.ReaderMapper;
import com.volkov.smartlibrarian.models.Reader;
import com.volkov.smartlibrarian.repositories.ReadersRepository;
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

    public List<Reader> findAll() {
        return checkIfExpired(readersRepository.findAllByOrderById());
    }

    public List<ReaderDTO> findAllDTOs() {
        var allByOrderById = findAll();
        return allByOrderById.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<Reader> findAllPerPage(int numberPage) {
        return checkIfExpired(readersRepository.findAllByOrderById(PageRequest.of(numberPage - 1, 5)));
    }

    public List<ReaderDTO> findAllDTOsPerPage(int numberPage) {
        var allPerPage = findAllPerPage(numberPage);
        return allPerPage.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<Reader> findAllSortedByYear() {
        return checkIfExpired(readersRepository.findAll(Sort.by("yearOfBirth")));
    }

    public List<ReaderDTO> findAllDTOsSortedByYear() {
        var allSortedByYear = findAllSortedByYear();
        return allSortedByYear.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<Reader> findAllPerPageSortedByYear(int numberPage) {
        return checkIfExpired(readersRepository.findAll(PageRequest.of(numberPage - 1, 5,
                Sort.by("yearOfBirth"))).getContent());
    }

    public List<ReaderDTO> findAllDTOsPerPageSortedByYear(int numberPage) {
        var allPerPageSortedByYear = findAllPerPageSortedByYear(numberPage);
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
    public void delete(Integer id) {
        readersRepository.deleteById(id);
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
