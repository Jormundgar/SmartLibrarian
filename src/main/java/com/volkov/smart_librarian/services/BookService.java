package com.volkov.smart_librarian.services;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import com.volkov.smart_librarian.repositories.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BooksRepository booksRepository;

    public List<Book> findAll() {
        return booksRepository.findAllByOrderById();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Book> findOneByName(String name) {
        return booksRepository.findByName(name).stream().findAny();
    }

    public Optional<Book> findOneByNameAndYearOfPublish(String name, int yearOfPublish) {
        return booksRepository.findByNameAndYearOfPublish(name, yearOfPublish).stream().findAny();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        updatedBook.setReader(booksRepository.findById(id).get().getReader());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person findBookReader(int id) {
        return booksRepository.findById(id).map(Book::getReader).orElse(null);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> book.setReader(null));
    }

    @Transactional
    public void assign(int id, Person person) {
        booksRepository.findById(id).ifPresent(book -> book.setReader(person));
    }

}
