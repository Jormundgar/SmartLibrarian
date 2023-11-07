package com.volkov.smartlibrarian.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;

@Getter
@Setter
public class ReaderDTO {

    @ReadOnlyProperty
    private Integer id;

    private String name;
    private int yearOfBirth;
    private List<String> readerBooks;
}
