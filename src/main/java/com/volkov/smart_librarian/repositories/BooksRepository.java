package com.volkov.smart_librarian.repositories;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);
    List<Book> findBooksByReaderId(int id);
    List<Book> findAllByOrderById();
    List<Book> findAllByOrderById(PageRequest pageRequest);
    Optional<Book> findByNameAndYearOfPublish(String name, int yearOfPublish);
}
