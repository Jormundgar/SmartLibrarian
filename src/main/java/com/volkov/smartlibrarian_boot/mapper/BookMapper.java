package com.volkov.smartlibrarian_boot.mapper;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.models.Book;
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
    @Mapping(source = "reader", target = "reader.name")
    Book bookDTOToBook(BookDTO bookDTO);

    default List<BookDTO> bookListToBookDtoList(List<Book> books) {
        return books.stream()
                .map(this::bookToBookDTO)
                .collect(Collectors.toList());
    }
}



