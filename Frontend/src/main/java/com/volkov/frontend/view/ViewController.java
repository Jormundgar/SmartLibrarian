package com.volkov.frontend.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksView() {
        return "books/index";
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.GET)
    public String searchView() {
        return "books/search";
    }

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String readersView() {
        return "readers/index";
    }
}

