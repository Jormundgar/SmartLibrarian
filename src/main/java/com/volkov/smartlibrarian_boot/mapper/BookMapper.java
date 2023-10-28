package com.volkov.smartlibrarian_boot.mapper;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDto(Book book);
    Book bookDtoToBook(BookDTO bookDTO);

    default List<BookDTO> bookListToBookDtoList(List<Book> books) {
        return books.stream()
                .map(this::bookToBookDto)
                .collect(Collectors.toList());
    }
}
