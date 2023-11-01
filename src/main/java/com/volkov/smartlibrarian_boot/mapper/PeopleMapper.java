package com.volkov.smartlibrarian_boot.mapper;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.dto.PersonDTO;
import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PeopleMapper {

    PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);

    @Mapping(target = "readerBooks", source = "readerBooks")
    PersonDTO personToPersonDTO(Person person);

    @Mapping(target = "readerBooks", source = "readerBooks")
    Person personDTOToPerson(PersonDTO personDTO);

    default List<String> mapBooksToStrings(List<Book> books) {
        return books.stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    default List<Book> mapStringsToBooks(List<String> bookStrings) {
        return bookStrings.stream()
                .map(Book::new)
                .collect(Collectors.toList());
    }

    default List<PersonDTO> personListToPersonDTOList(List<Person> people) {
        return people.stream()
                .map(this::personToPersonDTO)
                .collect(Collectors.toList());
    }
}
