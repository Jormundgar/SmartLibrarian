package com.volkov.smart_librarian.repositories;

import com.volkov.smart_librarian.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);

    Optional<Person> findByNameAndYearOfBirth(String name, int yearOfBirth);
}
