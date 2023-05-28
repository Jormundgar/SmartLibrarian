package com.volkov.smart_librarian.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name of book should not be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "The book must have the name of author")
    private String author;

    @Column(name = "year_of_publish")
    @Min(value = 0, message = "Year of publish should be greater than 0")
    private int yearOfPublish;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTake;

    @Transient
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person reader;


    public Book(String name, String author, int yearOfPublish) {
        this.name = name;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
    }
}
