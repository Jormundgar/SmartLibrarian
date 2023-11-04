package com.volkov.smartlibrarian_boot.mapper;

import com.volkov.smartlibrarian_boot.dto.ReaderDTO;
import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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
