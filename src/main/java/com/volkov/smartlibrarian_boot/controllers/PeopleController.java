package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.services.PersonService;
import com.volkov.smartlibrarian_boot.util.PeopleValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final PersonService personService;
    private final PeopleValidator peopleValidator;

    @GetMapping()
    public String index(Model model, @RequestParam(value = "byYear", required = false) boolean byYear) {
        List<Person> people;
        if (byYear) {
            people = personService.findAllSortedByYear();
        } else {
            people = personService.findAll();
        }
        model.addAttribute("people", people);
        model.addAttribute("lines", personService.getPeopleCount());
        model.addAttribute("byYearValue", byYear);
        return "people/index";
    }

    @GetMapping("/pages/{number}")
    public String indexPage(@PathVariable("number") int pageNumber, Model model,
                            @RequestParam(value = "byYear", required = false) boolean byYear ) {
        List<Person> people;
        if (byYear) {
            people = personService.findAllPerPageSortedByYear(pageNumber);
        } else {
            people = personService.findAllPerPage(pageNumber);
        }
        model.addAttribute("people", people);
        model.addAttribute("lines", personService.getPeopleCount());
        model.addAttribute("byYearValue", byYear);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        model.addAttribute("books", personService.findBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        peopleValidator.validateForUpdate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
