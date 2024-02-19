package com.example.application.repository.builders;

import com.example.application.dto.book.BookSearchParametersDto;
import com.example.application.model.Book;
import com.example.application.repository.providers.PriceSpecificationProvider;
import com.example.application.repository.providers.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;
    private final PriceSpecificationProvider<Book> priceSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametersDto) {
        Specification<Book> specification = Specification.where(null);
        if (searchParametersDto.authors() != null
                && searchParametersDto.authors().length > 0) {
            specification = specification
                    .and(getSpecificationByKey("author", searchParametersDto.authors()));
        }
        if (searchParametersDto.titles() != null
                && searchParametersDto.titles().length > 0) {
            specification = specification
                    .and(getSpecificationByKey("title", searchParametersDto.titles()));
        }
        if (searchParametersDto.priceFrom() != null || searchParametersDto.priceTo() != null) {
            specification = specification.and(priceSpecificationProviderManager
                    .getSpecification(searchParametersDto.priceFrom(),
                            searchParametersDto.priceTo()));
        }
        return specification;
    }

    private Specification<Book> getSpecificationByKey(String name, String [] params) {
        return Specification.where(bookSpecificationProviderManager
                .getSpecificationProvider(name)
                .getSpecification(params));
    }
}
