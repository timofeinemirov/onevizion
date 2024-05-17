package com.onevision.job.controller;

import com.onevision.job.model.Book;
import com.onevision.job.model.BookGroup;
import com.onevision.job.model.BookSearchResult;
import com.onevision.job.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping("/by-author")
    public List<BookGroup> findAllGrouped() {
        return bookService.findAllGrouped();
    }

    @GetMapping("/search")
    public List<BookSearchResult> searchByTitle(@RequestParam(name = "title") String title) {
        return bookService.searchByTitle(title);
    }


}
