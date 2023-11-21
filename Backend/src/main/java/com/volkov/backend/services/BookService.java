package com.volkov.backend.services;

import com.volkov.backend.dto.BookDTO;
import com.volkov.backend.dto.RecordsNumberDTO;
import com.volkov.backend.mapper.BookMapper;
import com.volkov.backend.models.Book;
import com.volkov.backend.repositories.BooksRepository;
import com.volkov.backend.repositories.ReadersRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class BookService {

    private final BooksRepository booksRepository;
    private final ReadersRepository readersRepository;
    private final BookMapper bookMapper;

    private List<Book> checkIfExpiredList(List<Book> books) {
        books.forEach(this::checkIfExpired);
        return books;
    }

    private void checkIfExpired(Book book) {
        if (book.getDateOfTake() != null) {
            var check = Math.abs(book.getDateOfTake().getTime() - new Date().getTime());
            long bookedFor = 864000000;
            book.setExpired(check > bookedFor);
        }
    }

    public List<BookDTO> findAllDTOs() {
        var books = checkIfExpiredList(booksRepository.findAllByOrderById());
        return bookMapper.bookListToBookDtoList(books);
    }

    public List<BookDTO> findAllDTOsPerPage(int numberPage) {
        var allPerPage = checkIfExpiredList(booksRepository.
                findAllByOrderById(PageRequest.of(numberPage - 1, 5)));
        return allPerPage.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    public List<BookDTO> findAllDTOsSortedByYear() {
        var allSortedByYear = checkIfExpiredList(booksRepository.findAll(Sort.by("yearOfPublish")));
        return allSortedByYear.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    public List<BookDTO> findAllDTOsPerPageSortedByYear(int numberPage) {
        var allPerPageSortedByYear = checkIfExpiredList(
                booksRepository
                        .findAll(PageRequest.of(numberPage - 1, 5, Sort.by("yearOfPublish")))
                        .getContent()
        );
        return allPerPageSortedByYear.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    public Optional<BookDTO> findById(Integer id) {
        var book = booksRepository.findById(id);
        book.ifPresent(this::checkIfExpired);
        return book.map(bookMapper::bookToBookDTO);
    }

    public List<BookDTO> findByReaderId(Integer id) {
        var book = checkIfExpiredList(booksRepository.findByReaderId(id));
        return book.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    @Transactional
    public BookDTO saveDto(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedBook = booksRepository.save(book);
        return bookMapper.bookToBookDTO(savedBook);
    }

    @Transactional
    public Optional<BookDTO> updateDTO(BookDTO bookDTO) {
        var bookFromDB = booksRepository.findById(bookDTO.getId());
        var newBook = bookMapper.bookDTOToBook(bookDTO);
        Book updatedBook;
        if (bookFromDB.isEmpty()) {
            return Optional.empty();
        } else {
            updatedBook = bookFromDB.get();
        }
        updatedBook.setName(newBook.getName());
        updatedBook.setAuthor(newBook.getAuthor());
        updatedBook.setYearOfPublish(newBook.getYearOfPublish());
        booksRepository.save(updatedBook);
        return Optional.of(bookMapper.bookToBookDTO(updatedBook));
    }

    @Transactional
    public Optional<Book> deleteDTO(Integer id) {
        var optionalSavedBook = booksRepository.findById(id);
        if (optionalSavedBook.isPresent()) {
            booksRepository.deleteById(id);
        }
        return optionalSavedBook;
    }

    @Transactional
    public Optional<BookDTO> releaseDTO(Integer id) {
        var book = booksRepository.findById(id);
        book.ifPresent(singleBook -> {
            singleBook.setReader(null);
            singleBook.setDateOfTake(null);
        });
        return book.map(bookMapper::bookToBookDTO);
    }

    @Transactional
    public Book assignDTO(BookDTO bookDTO) {
        var reader = readersRepository.findByName(bookDTO.getReader()).stream().findFirst();
        var assignBook = booksRepository.findById(bookDTO.getId());
        Book book = null;
        if (assignBook.isPresent()) {
            book = assignBook.get();
            book.setReader(reader.get());
            book.setDateOfTake(new Date());
        }
        return book;
    }

    public RecordsNumberDTO getBooksCountDto() {
        var count = booksRepository.count();
        return new RecordsNumberDTO(count);
    }

    public List<BookDTO> searchDTOs(String contain) {
        var search = booksRepository.findByNameContaining(contain);
        return search.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }
}
