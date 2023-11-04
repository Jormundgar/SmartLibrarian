package com.volkov.smartlibrarian.services;

import com.volkov.smartlibrarian.dto.ReaderDTO;
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
        var allByOrderById = checkIfExpired(readersRepository.findAllByOrderById());
        return allByOrderById.stream().map(readerMapper::readerToReaderDTO).collect(Collectors.toList());
    }

    public List<Reader> findAllPerPage(int numberPage) {
        return checkIfExpired(readersRepository.findAllByOrderById(PageRequest.of(numberPage, 5)));
    }

    public List<Reader> findAllSortedByYear() {
        return checkIfExpired(readersRepository.findAll(Sort.by("yearOfBirth")));
    }

    public List<Reader> findAllPerPageSortedByYear(int numberPage) {
        return checkIfExpired(readersRepository.findAll(PageRequest.of(numberPage, 5,
                Sort.by("yearOfBirth"))).getContent());
    }

    private List<Reader> checkIfExpired(List<Reader> readers) {
        readers.forEach(reader -> reader.getReaderBooks().forEach(book -> {
            var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
            long bookedFor = 864000000;
            book.setExpired(check > bookedFor);
        }));
        return readers;
    }

    public Optional<Reader> findOneByName(String name) {
        return readersRepository.findByName(name).stream().findAny();
    }

    public Optional<Reader> findOneByNameAndYearOfBirth(String name, int yearOfBirth) {
        return readersRepository.findByNameAndYearOfBirth(name, yearOfBirth).stream().findAny();
    }

    @Transactional
    public void save(Reader reader) {
        readersRepository.save(reader);
    }

    @Transactional
    public void update(int id, Reader updatedReader) {
        updatedReader.setId(id);
        readersRepository.save(updatedReader);
    }

    @Transactional
    public void delete(int id) {
        readersRepository.deleteById(id);
    }

    public int getPeopleCount() {
        return (int) readersRepository.count();
    }
}
