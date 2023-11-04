package com.volkov.smartlibrarian.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private String name;
    private String author;
    private int yearOfPublish;
    private String dateOfTake;
    private boolean expired;
    private String reader;
}
