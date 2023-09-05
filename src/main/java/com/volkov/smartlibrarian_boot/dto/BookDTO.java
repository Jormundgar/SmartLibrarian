package com.volkov.smartlibrarian_boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
