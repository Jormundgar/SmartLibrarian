package com.volkov.smartlibrarian_boot.controllers;

import com.volkov.smartlibrarian_boot.models.Book;
import com.volkov.smartlibrarian_boot.models.Person;
import com.volkov.smartlibrarian_boot.services.BookService;
import com.volkov.smartlibrarian_boot.services.PersonService;
import com.volkov.smartlibrarian_boot.util.BookValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PersonService personService;
    private final BookValidator bookValidator;

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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        var bookOwner = bookService.findBookReader(id);
        if (bookOwner != null) {
            model.addAttribute("owner", bookOwner);
        }
        else {
            model.addAttribute("people", personService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validateForUpdate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.assign(id, person);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String getSearch(Model model) {
        model.addAttribute("database", false);
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam("contain") String contain, Model model) {
        model.addAttribute("database", true);
        model.addAttribute("books", bookService.search(contain));
        return "books/search";
    }
}

