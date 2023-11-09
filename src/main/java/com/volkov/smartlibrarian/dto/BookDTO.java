package com.volkov.smartlibrarian.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
@Setter
public class BookDTO {

    @ReadOnlyProperty
    private Integer id;

    private String name;
    private String author;
    private int yearOfPublish;
    private String dateOfTake;
    private boolean expired;
    private String reader;
}
