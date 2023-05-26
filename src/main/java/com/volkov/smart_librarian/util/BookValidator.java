package com.volkov.smart_librarian.util;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import com.volkov.smart_librarian.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookValidator implements Validator {

    private final BookService bookService;

    @Override
    public boolean supports(Class<?> sClass) {
        return Book.class.equals(sClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var book = (Book) target;
        if(bookService.findOneByName(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "This book already exist");
        }
    }

    public void validateForUpdate(Object target, Errors errors) {
        var book = (Book) target;
        if(bookService.findOneByNameAndYearOfPublish(book.getName(), book.getYearOfPublish()).isPresent()) {
            errors.rejectValue("name", "", "This book already exist");
            errors.rejectValue("yearOfPublish", "", "This book already exist");
        }
    }
}
