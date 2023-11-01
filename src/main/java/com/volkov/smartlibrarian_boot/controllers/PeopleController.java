package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class
PeopleController {

    private final PersonService personService;

    @GetMapping
    public ModelAndView index(@ModelAttribute("new_user") Person person,
                              @RequestParam(value = "byYear", required = false) boolean byYear,
                              @ModelAttribute("book") Book book) {
        var modelAndView = new ModelAndView("people/index");
        List<Person> people;
        if (byYear) {
            people = personService.findAllSortedByYear();
        } else {
            people = personService.findAll();
        }
        modelAndView.addObject("people", people);
        modelAndView.addObject("lines", personService.getPeopleCount());
        modelAndView.addObject("byYearValue", byYear);
        return modelAndView;
    }

    @GetMapping("/pages/{number}")
    public ModelAndView indexPage(@ModelAttribute("new_user") Person person,
                                  @PathVariable("number") int pageNumber,
                                  @RequestParam(value = "byYear", required = false) boolean byYear,
                                  @ModelAttribute("book") Book book) {
        var modelAndView = new ModelAndView("people/index");
        List<Person> people;
        if (byYear) {
            people = personService.findAllPerPageSortedByYear(pageNumber);
        } else {
            people = personService.findAllPerPage(pageNumber);
        }
        modelAndView.addObject("people", people);
        modelAndView.addObject("lines", personService.getPeopleCount());
        modelAndView.addObject("byYearValue", byYear);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("new_user") Person person) {
        var modelAndView = new ModelAndView("redirect:/people");
        personService.save(person);
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("person") Person person,
                               @PathVariable("id") int id) {
        var modelAndView = new ModelAndView("redirect:/people");
        personService.update(id, person);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        var modelAndView = new ModelAndView("redirect:/people");
        personService.delete(id);
        return modelAndView;
    }
}
