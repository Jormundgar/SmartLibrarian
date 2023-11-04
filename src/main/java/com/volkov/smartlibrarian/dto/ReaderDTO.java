package com.volkov.smartlibrarian.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReaderDTO {

    private String name;
    private int yearOfBirth;
    private List<String> readerBooks;
}
