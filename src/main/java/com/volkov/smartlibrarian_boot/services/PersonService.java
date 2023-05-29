package com.volkov.smartlibrarian_boot.services;

import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.repositories.BooksRepository;
import com.volkov.smartlibrarian_boot.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public List<Person> findAllSortedByYear() {
        return peopleRepository.findAll(Sort.by("yearOfBirth"));
    }

    public List<Person> findAllPerPageSortedByYear(int numberPage) {
        return peopleRepository.findAll(PageRequest.of(numberPage, 5, Sort.by("yearOfBirth")))
                .getContent();

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
        var books = booksRepository.findBooksByReaderId(id);
        books.forEach(book -> {
            var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
            long bookedFor = 864000000;
            if (check > bookedFor) {
                book.setExpired(true);
            } else {
                book.setExpired(false);
            }});
        return books;
    }

    public int getPeopleCount() {
        return (int) peopleRepository.count();
    }
}
