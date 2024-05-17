package com.onevizion.job.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String DESCRIPTION = "description";

    private Integer id;

    private String title;

    private String author;

    private String description;

}
