package com.volkov.smart_librarian.util;

import com.volkov.smart_librarian.dao.BookDAO;
import com.volkov.smart_librarian.models.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Override
    public boolean supports(Class<?> sClass) {
        return Book.class.equals(sClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var book = (Book) target;
        if(bookDAO.show(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "This book already exist");
        }
    }
}
