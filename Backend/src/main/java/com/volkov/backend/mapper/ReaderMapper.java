package com.volkov.backend.mapper;

import com.volkov.backend.models.Reader;
import com.volkov.backend.dto.ReaderDTO;
import com.volkov.backend.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReaderMapper {

    @Mapping(target = "readerBooks", source = "readerBooks")
    ReaderDTO readerToReaderDTO(Reader reader);

    @Mapping(target = "readerBooks", source = "readerBooks")
    Reader readerDTOToReader(ReaderDTO readerDTO);

    default List<String> mapBooksToStrings(List<Book> books) {
        return books.stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    default List<Book> mapStringsToBooks(List<String> bookStrings) {
        if (bookStrings == null || bookStrings.isEmpty()) {
            return Collections.emptyList();
        }
        return bookStrings.stream()
                .map(Book::new)
                .collect(Collectors.toList());
    }

    default List<ReaderDTO> readerListToReaderDTOList(List<Reader> readers) {
        return readers.stream()
                .map(this::readerToReaderDTO)
                .collect(Collectors.toList());
    }
}
