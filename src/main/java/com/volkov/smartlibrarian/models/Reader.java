package com.volkov.smartlibrarian.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "readers")
@NoArgsConstructor
@Getter
@Setter
public class Reader {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your name should be in this format: Name Surname")
    private String name;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    private int yearOfBirth;

    @OneToMany(mappedBy = "reader")
    private List<Book> readerBooks;

    public Reader(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }
}
