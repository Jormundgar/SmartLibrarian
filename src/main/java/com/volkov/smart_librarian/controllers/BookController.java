package com.volkov.smart_librarian.controllers;

import com.volkov.smart_librarian.dao.BookDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookDAO bookDAO;
}
