package com.example.application.repository.providers;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface SpecificationProvider<T> {
    String getKey();

    Specification<T> getSpecification(String[] params);
}
