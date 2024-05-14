package com.llakhmann.backend.repositories;

import com.llakhmann.backend.models.Reader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReadersRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByName(String name);
    List<Reader> findAllByOrderById();
    List<Reader> findAllByOrderById(PageRequest pageRequest);
}
