package com.volkov.security.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksView() {
        return "books/index";
    }

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String readersView() {
        return "readers/index";
    }

    @RequestMapping(value = "/usertest", method = RequestMethod.GET)
    public String userView() {
        return "usertest/index";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }
}

