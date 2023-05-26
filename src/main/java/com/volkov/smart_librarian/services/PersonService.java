package com.volkov.smart_librarian.services;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import com.volkov.smart_librarian.repositories.BooksRepository;
import com.volkov.smart_librarian.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    public List<Person> findAll() {
        return peopleRepository.findAllByOrderById();
    }

    public List<Person> findAllPerPage(int numberPage) {
        return peopleRepository.findAllByOrderById(PageRequest.of(numberPage, 5));
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
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

    public List<Book> findBooksByPersonId(int id) {
        return booksRepository.findBooksByReaderId(id);
    }

    public int getPeopleCount() {
        return (int) peopleRepository.count();
    }
 }
