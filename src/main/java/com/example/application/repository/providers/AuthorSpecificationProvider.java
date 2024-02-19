package com.example.application.repository.providers;

import com.example.application.model.Book;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR = "author";

    @Override
    public String getKey() {
        return AUTHOR;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> root.get(AUTHOR)
                .in(Arrays.stream(params)
                        .toArray()));
    }
}
