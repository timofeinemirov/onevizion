package com.onevizion.job.service;

import com.onevizion.job.model.Book;
import com.onevizion.job.model.BookGroup;
import com.onevizion.job.model.BookSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final int SEARCH_RESULT_LIMIT = 10;

    private static final String BOOK_LIST_SQL = "SELECT id, title, author, description FROM book";
    private static final String BOOK_CREATE_SQL = "INSERT INTO book (title, author, description) VALUES (?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        return jdbcTemplate.query(BOOK_LIST_SQL, new BookMapper());
    }

    public Book create(Book book) {
        jdbcTemplate.update(BOOK_CREATE_SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );
        return book;
    }

    public List<BookGroup> findAllGrouped() {
        return findAll().stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet().stream()
                .map(e -> new BookGroup(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<BookSearchResult> searchByTitle(String title) {
        return findAll().stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet().stream()
                .map(e -> {
                    int count = e.getValue().stream()
                            .map(Book::getTitle)
                            .mapToInt(t -> StringUtils.countOccurrencesOf(t.toLowerCase(), title.toLowerCase()))
                            .sum();
                    return new BookSearchResult(e.getKey(), count);
                })
                .filter(r -> r.getCount() > 0)
                .sorted(Comparator.comparingInt(BookSearchResult::getCount).reversed())
                .limit(SEARCH_RESULT_LIMIT)
                .collect(Collectors.toList());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setDescription(rs.getString("description"));
            return book;
        }
    }

}
