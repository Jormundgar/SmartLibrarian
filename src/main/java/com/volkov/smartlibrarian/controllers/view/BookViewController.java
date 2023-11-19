package com.volkov.smartlibrarian.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookViewController {
    @GetMapping
    public String readersView() {
        return "books/index";
    }

    @GetMapping("/search")
    public String searchView() {
        return "books/search";
    }
}
