package com.example.application.service.book;

import com.example.application.dto.book.BookDto;
import com.example.application.dto.book.BookDtoWithoutCategoriesIds;
import com.example.application.dto.book.BookSearchParametersDto;
import com.example.application.dto.book.CreateBookRequestDto;
import com.example.application.exception.EntityNotFoundException;
import com.example.application.mapper.BookMapper;
import com.example.application.model.Book;
import com.example.application.repository.BookRepository;
import com.example.application.repository.builders.BookSpecificationBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder specificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can not find book by id:" + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public List<BookDtoWithoutCategoriesIds> getBooksByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategoriesIds)
                .toList();
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable) {
        Specification<Book> specification = specificationBuilder.build(searchParameters);
        return bookRepository.findAll(specification, pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
