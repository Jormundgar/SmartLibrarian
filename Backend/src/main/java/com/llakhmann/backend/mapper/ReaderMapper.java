package com.llakhmann.backend.mapper;

import com.llakhmann.backend.models.Book;
import com.llakhmann.backend.models.Reader;
import com.llakhmann.backend.dto.ReaderDTO;
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
