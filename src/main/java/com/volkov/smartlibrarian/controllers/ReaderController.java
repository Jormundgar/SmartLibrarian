package com.volkov.smartlibrarian.controllers;

import com.volkov.smartlibrarian.models.Book;
import com.volkov.smartlibrarian.models.Reader;
import com.volkov.smartlibrarian.services.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/readers")
@AllArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping
    public ModelAndView index(@ModelAttribute("new_reader") Reader reader,
                              @RequestParam(value = "byYear", required = false) boolean byYear,
                              @ModelAttribute("book") Book book) {
        var modelAndView = new ModelAndView("readers/index");
        List<Reader> readers;
        if (byYear) {
            readers = readerService.findAllSortedByYear();
        } else {
            readers = readerService.findAll();
        }
        modelAndView.addObject("readers", readers);
        modelAndView.addObject("lines", readerService.getPeopleCount());
        modelAndView.addObject("byYearValue", byYear);
        return modelAndView;
    }

    @GetMapping("/pages/{number}")
    public ModelAndView indexPage(@ModelAttribute("new_reader") Reader reader,
                                  @PathVariable("number") Integer pageNumber,
                                  @RequestParam(value = "byYear", required = false) boolean byYear,
                                  @ModelAttribute("book") Book book) {
        var modelAndView = new ModelAndView("readers/index");
        List<Reader> readers;
        if (byYear) {
            readers = readerService.findAllPerPageSortedByYear(pageNumber+1);
        } else {
            readers = readerService.findAllPerPage(pageNumber+1);
        }
        modelAndView.addObject("readers", readers);
        modelAndView.addObject("lines", readerService.getPeopleCount());
        modelAndView.addObject("byYearValue", byYear);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("new_reader") Reader reader) {
        var modelAndView = new ModelAndView("redirect:/readers");
        try {
            readerService.save(reader);
        } catch (IllegalStateException e) {
            return modelAndView;
        }
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("reader") Reader reader,
                               @PathVariable("id") Integer id) {
        var modelAndView = new ModelAndView("redirect:/readers");
        readerService.update(id, reader);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {
        var modelAndView = new ModelAndView("redirect:/readers");
        readerService.delete(id);
        return modelAndView;
    }
}
