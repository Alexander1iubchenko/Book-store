package com.example.application.repository.providers;

import com.example.application.model.Book;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;

public class TitlesSpecificationProvider implements SpecificationProvider<Book> {
    private static final String TITLE = "title";

    @Override
    public String getKey() {
        return TITLE;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> root.get(TITLE)
                .in(Arrays.stream(params)
                        .toArray()));
    }
}
