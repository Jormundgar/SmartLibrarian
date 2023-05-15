package com.volkov.smart_librarian.controllers;

import com.volkov.smart_librarian.dao.PersonDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleController {

    private final PersonDAO personDAO;

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
}
