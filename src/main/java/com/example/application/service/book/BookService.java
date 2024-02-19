package com.example.application.service.book;

import com.example.application.dto.book.BookDto;
import com.example.application.dto.book.BookDtoWithoutCategoriesIds;
import com.example.application.dto.book.BookSearchParametersDto;
import com.example.application.dto.book.CreateBookRequestDto;
import com.example.application.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    void deleteById(Long id);

    void update(Long id, Book book);

    List<BookDtoWithoutCategoriesIds> getBooksByCategoryId(Long id, Pageable pageable);

    List<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable);
}
