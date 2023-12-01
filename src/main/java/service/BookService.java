package service;

import dto.BookDto;
import dto.CreateBookRequestDto;
import java.util.List;
import model.Book;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    void deleteById(Long id);

    void update(Long id, Book book);
}
