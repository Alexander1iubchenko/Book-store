package com.example.application.repository.providers;

import com.example.application.model.Book;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProviderManager implements PriceSpecificationProvider<Book> {
    private static final String PRICE = "price";

    @Override
    public Specification<Book> getSpecification(BigDecimal from, BigDecimal to) {
        return (root, query, criteriaBuilder) -> {
            if (from != null && to != null) {
                return criteriaBuilder.between(root.get(PRICE), from, to);
            }
            if (from != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), from);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), to);
        };
    }
}
