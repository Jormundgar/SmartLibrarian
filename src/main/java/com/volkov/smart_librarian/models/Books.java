package com.volkov.smart_librarian.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Books {
    private int id;
    @NotEmpty(message = "Name of book should not be empty")
    private String name;
    @NotEmpty(message = "The book must have the name of author")
    private String author;
    @Min(value = 0, message = "Year of publish should be greater than 0")
    private int year_of_publish;
}
