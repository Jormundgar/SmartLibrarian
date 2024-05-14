package com.llakhmann.backend.repositories;

import com.llakhmann.backend.models.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderById();
    List<Book> findAllByOrderById(PageRequest pageRequest);
    List<Book> findByNameContaining(String contain);
    List<Book> findByReaderId(Integer id);
}
