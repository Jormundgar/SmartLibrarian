package com.volkov.smart_librarian.dao;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY id", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO books(name, author, year_of_publish) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear_of_publish());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE books SET name = ?, author = ?, year_of_publish = ? WHERE id = ?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear_of_publish(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    public Person showBookOwner(int id) {
        return jdbcTemplate.query("SELECT p.name, p.year_of_birth FROM books JOIN persons p on p.id = books.person_id WHERE books.id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE books SET person_id = NULL WHERE id = ?", id);
    }

    public void assign(int id, int userID) {
        jdbcTemplate.update("UPDATE books SET person_id = ? WHERE id = ?", userID, id);
    }
}
