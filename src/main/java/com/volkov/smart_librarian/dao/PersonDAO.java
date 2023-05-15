package com.volkov.smart_librarian.dao;

import com.volkov.smart_librarian.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM persons", new BeanPropertyRowMapper<>(Person.class));
    }
}
