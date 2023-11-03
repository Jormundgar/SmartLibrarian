package com.volkov.smartlibrarian_boot.services;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.mapper.BookMapper;
import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.repositories.BooksRepository;
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
public class BookService {

    private final BooksRepository booksRepository;
    private final BookMapper bookMapper;

    public List<Book> findAll() {
        return booksRepository.findAllByOrderById();
    }

    public List<BookDTO> findAllDTOs() {
        var books = booksRepository.findAllByOrderById();
        books.forEach(book -> {
            if (book.getDateOfTake() != null) {
                var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
                long bookedFor = 864000000;
                book.setExpired(check > bookedFor);
            }
        });
        return bookMapper.bookListToBookDtoList(books);
    }

    public List<Book> findAllPerPage(int numberPage) {
        return booksRepository.findAllByOrderById(PageRequest.of(numberPage, 5));
    }

    public List<Book> findAllSortedByYear() {
        return booksRepository.findAll(Sort.by("yearOfPublish"));
    }

    public List<Book> findAllPerPageSortedByYear(int numberPage) {
        return booksRepository
                .findAll(PageRequest.of(numberPage, 5, Sort.by("yearOfPublish")))
                .getContent();
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
        updatedBook.setDateOfTake(booksRepository.findById(id).get().getDateOfTake());
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
        booksRepository.findById(id).ifPresent(book -> {
            book.setReader(null);
            book.setDateOfTake(null);
        });
    }

    @Transactional
    public void assign(int id, Person person) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setReader(person);
            book.setDateOfTake(new Date());
        });
    }

    public int getBooksCount() {
        return (int) booksRepository.count();
    }

    public List<Book> search(String contain) {
        return booksRepository.findByNameContaining(contain);
    }
}
