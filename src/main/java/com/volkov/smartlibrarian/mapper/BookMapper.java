package com.volkov.smartlibrarian.mapper;

import com.volkov.smartlibrarian.dto.BookDTO;
import com.volkov.smartlibrarian.models.Book;
import com.volkov.smartlibrarian.models.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    @Mapping(source = "dateOfTake", target = "dateOfTake", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "reader.name", target = "reader")
    BookDTO bookToBookDTO(Book book);

    @Mapping(source = "dateOfTake", target = "dateOfTake", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "reader", target = "reader")
    Book bookDTOToBook(BookDTO bookDTO);

    default List<BookDTO> bookListToBookDtoList(List<Book> books) {
        return books.stream()
                .map(this::bookToBookDTO)
                .collect(Collectors.toList());
    }

    default Reader map(String value) {
        if (value == null) {
            return null;
        }
        Reader reader = new Reader();
        reader.setName(value);
        return reader;
    }
}



