package com.onevizion.job.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchResult {

    private String author;

    private Integer count;

    public BookSearchResult(String author, Integer count) {
        this.author = author;
        this.count = count;
    }
}
