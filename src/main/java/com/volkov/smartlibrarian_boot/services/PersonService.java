package com.volkov.smartlibrarian_boot.services;

import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PersonService {

    private final PeopleRepository peopleRepository;

    public List<Person> findAll() {
        return checkIfExpired(peopleRepository.findAllByOrderById());
    }

    public List<Person> findAllPerPage(int numberPage) {
        return checkIfExpired(peopleRepository.findAllByOrderById(PageRequest.of(numberPage, 5)));
    }

    public List<Person> findAllSortedByYear() {
        return checkIfExpired(peopleRepository.findAll(Sort.by("yearOfBirth")));
    }

    public List<Person> findAllPerPageSortedByYear(int numberPage) {
        return checkIfExpired(peopleRepository.findAll(PageRequest.of(numberPage, 5,
                Sort.by("yearOfBirth"))).getContent());
    }

    private List<Person> checkIfExpired(List<Person> users) {
        users.forEach(reader -> reader.getReaderBooks().forEach(book -> {
            var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
            long bookedFor = 864000000;
            book.setExpired(check > bookedFor);
        }));
        return users;
    }

    public Optional<Person> findOneByName(String name) {
        return peopleRepository.findByName(name).stream().findAny();
    }

    public Optional<Person> findOneByNameAndYearOfBirth(String name, int yearOfBirth) {
        return peopleRepository.findByNameAndYearOfBirth(name, yearOfBirth).stream().findAny();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public int getPeopleCount() {
        return (int) peopleRepository.count();
    }
}
