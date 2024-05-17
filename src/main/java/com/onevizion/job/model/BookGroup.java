package com.onevizion.job.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookGroup {

    private String author;

    private List<Book> books;

    public BookGroup(String author, List<Book> books) {
        this.author = author;
        this.books = books;
    }
}
