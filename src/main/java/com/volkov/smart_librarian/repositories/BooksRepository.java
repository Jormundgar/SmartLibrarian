package com.volkov.smart_librarian.repositories;

import com.volkov.smart_librarian.models.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByReaderId(int id);

    // without pagination and sorting by year
    List<Book> findAllByOrderById();

    // with pagination and without sorting by year
    List<Book> findAllByOrderById(PageRequest pageRequest);

    // for Book Validator (Create page)
    Optional<Book> findByName(String name);

    // for Book Validator (Edit page)
    Optional<Book> findByNameAndYearOfPublish(String name, int yearOfPublish);
}
