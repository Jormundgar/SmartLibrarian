package com.volkov.smartlibrarian.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;

@Getter
@Setter
@Schema(description = "Reader entity")
public class ReaderDTO {

    @Schema(description = "UID")
    private Integer id;
    @Schema(description = "Reader`s name")
    private String name;
    @Schema(description = "Year of birth")
    private int yearOfBirth;
    @Schema(description = "Reader`s book list")
    private List<String> readerBooks;
}
