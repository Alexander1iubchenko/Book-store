package service;

import dto.book.BookDto;
import dto.book.BookDtoWithoutCategoriesIds;
import dto.book.CreateBookRequestDto;
import java.util.List;
import model.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    void deleteById(Long id);

    void update(Long id, Book book);

    List<BookDtoWithoutCategoriesIds> getBooksByCategoryId(Long id);
}
