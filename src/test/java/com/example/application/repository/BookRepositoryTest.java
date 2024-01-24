package com.example.application.repository;

import com.example.application.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:database/books/add-books-to-books-table.sql"})
    public void findAll_NonEmptyDb_ReturnThreeBooks() {

        PageRequest pageable = PageRequest.of(0, 10);
        Page<Book> result = bookRepository.findAll(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(6, result.getTotalElements());
    }
}
