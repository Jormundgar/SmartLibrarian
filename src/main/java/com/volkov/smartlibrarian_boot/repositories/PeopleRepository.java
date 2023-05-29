package com.volkov.smartlibrarian_boot.repositories;

import com.volkov.smartlibrarian_boot.models.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
    Optional<Person> findByNameAndYearOfBirth(String name, int yearOfBirth);
    List<Person> findAllByOrderById();
    List<Person> findAllByOrderById(PageRequest pageRequest);
}
