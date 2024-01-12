package com.example.application.mapper;

import com.example.application.config.MapperConfig;
import com.example.application.dto.book.BookDto;
import com.example.application.dto.book.BookDtoWithoutCategoriesIds;
import com.example.application.dto.book.CreateBookRequestDto;
import com.example.application.model.Book;
import com.example.application.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "categoriesIds", ignore = true)
    BookDto toDto(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoriesIds toDtoWithoutCategoriesIds(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> categoriesIdsSet = book.getCategories()
                .stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoriesIds(categoriesIdsSet);
    }
}
