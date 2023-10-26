package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.services.BookService;
import com.volkov.smartlibrarian_boot.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @GetMapping
    public ModelAndView index(@ModelAttribute("new_book") Book book,
                              @RequestParam(value = "byYear", required = false) boolean byYear,
                              @ModelAttribute("person") Person person) {
        var modelAndView = new ModelAndView("books/index");
        List<Book> books;
        if (byYear) {
            books = bookService.findAllSortedByYear();
        } else {
            books = bookService.findAll();
        }
        modelAndView.addObject("books", books);
        modelAndView.addObject("lines", bookService.getBooksCount());
        modelAndView.addObject("byYearValue", byYear);
        modelAndView.addObject("people", personService.findAll());
        return modelAndView;
    }

    @GetMapping("/pages/{number}")
    public ModelAndView indexPage(@ModelAttribute("new_book") Book book,
                                  @PathVariable("number") int pageNumber,
                                  @RequestParam(value = "byYear", required = false) boolean byYear,
                                  @ModelAttribute("person") Person person) {
        var modelAndView = new ModelAndView("books/index");
        List<Book> books;
        if (byYear) {
            books = bookService.findAllPerPageSortedByYear(pageNumber);
        } else {
            books = bookService.findAllPerPage(pageNumber);
        }
        modelAndView.addObject("books", books);
        modelAndView.addObject("lines", bookService.getBooksCount());
        modelAndView.addObject("byYearValue", byYear);
        modelAndView.addObject("people", personService.findAll());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("new_book") Book book) {
        var modelAndView = new ModelAndView("redirect:/books");
        bookService.save(book);
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("book") Book book,
                               @PathVariable("id") int id) {
        var modelAndView = new ModelAndView("redirect:/books");
        bookService.update(id, book);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        var modelAndView = new ModelAndView("redirect:/books");
        bookService.delete(id);
        return modelAndView;
    }

    @PatchMapping("/{id}/release")
    public ModelAndView release(@PathVariable("id") int id) {
        var modelAndView = new ModelAndView("redirect:/books");
        bookService.release(id);
        return modelAndView;
    }

    @PatchMapping("/{id}/assign")
    public ModelAndView assign(@PathVariable("id") int id,
                               @ModelAttribute("person") Person person) {
        var modelAndView = new ModelAndView("redirect:/books");
        bookService.assign(id, person);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("contain") String contain) {
        var modelAndView = new ModelAndView("books/search");
        //TODO: Figure out is "database" attribute necessary due to new version of index.html
        modelAndView.addObject("database", true);
        modelAndView.addObject("books", bookService.search(contain));
        return modelAndView;
    }
}

