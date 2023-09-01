package com.volkov.smartlibrarian_boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDTO {

    private String name;
    private int yearOfBirth;
    private List<String> readerBooks;
}
