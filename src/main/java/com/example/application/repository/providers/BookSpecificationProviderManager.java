package com.example.application.repository.providers;

import com.example.application.model.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private static final String EXCEPTION
            = "Can not find specificationProvider by key ";
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream().filter(spec -> spec.getKey()
                        .equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(EXCEPTION + key));
    }
}
