package com.volkov.smart_librarian.controllers;

import com.volkov.smart_librarian.models.Book;
import com.volkov.smart_librarian.models.Person;
import com.volkov.smart_librarian.services.BookService;
import com.volkov.smart_librarian.services.PersonService;
import com.volkov.smart_librarian.util.BookValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookService bookService;
    private final PersonService personService;
    private final BookValidator bookValidator;

    @GetMapping()
    public String index(Model model, @RequestParam(value = "byYear", required = false) boolean byYear) {
        List<Book> books;
        if (byYear) {
            books = bookService.findAllSortedByYear();
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("lines", bookService.getBooksCount());
        return "books/index";
    }

    @GetMapping("/pages/{number}")
    public String indexPage(@PathVariable("number") int pageNumber, Model model,
                            @RequestParam(value = "byYear", required = false) boolean byYear) {
        List<Book> books;
        if (byYear) {
            books = bookService.findAllPerPageSortedByYear(pageNumber);
        } else {
            books = bookService.findAllPerPage(pageNumber);
        }
        model.addAttribute("books", books);
        model.addAttribute("lines", bookService.getBooksCount());
        return "books/index";
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

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookService.save(book);
        return "redirect:/books";
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
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.assign(id, person);
        return "redirect:/books/" + id;
    }

}
