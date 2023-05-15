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
public class People {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Min(value = 0, message = "Year of birth should be greater than 0")
    private int year_of_birth;
}
