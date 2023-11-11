package com.volkov.smartlibrarian.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
@Setter
@Schema(description = "Book entity")
public class BookDTO {

    @ReadOnlyProperty
    @Schema(description = "Book UID")
    private Integer id;
    @Schema(description = "Book`s title")
    private String name;
    @Schema(description = "Book`s name of author")
    private String author;
    @Schema(description = "Book`s year of publish")
    private int yearOfPublish;
    @Schema(description = "Book`s date of assigning to reader")
    private String dateOfTake;
    @Schema(description = "Book`s check for expiring")
    private boolean expired;
    @Schema(description = "Book`s reader name")
    private String reader;
}
