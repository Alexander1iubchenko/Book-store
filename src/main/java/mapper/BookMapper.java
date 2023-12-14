package mapper;

import config.MapperConfig;
import dto.book.BookDto;
import dto.book.CreateBookRequestDto;
import model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
