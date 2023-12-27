package mapper;

import config.MapperConfig;
import dto.book.BookDto;
import dto.book.BookDtoWithoutCategoriesIds;
import dto.book.CreateBookRequestDto;
import java.util.Set;
import java.util.stream.Collectors;
import model.Book;
import model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

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
