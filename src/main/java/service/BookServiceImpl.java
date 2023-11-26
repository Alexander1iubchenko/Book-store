package service;

import dto.BookDto;
import dto.CreateBookRequestDto;
import exception.EntityNotFoundException;
import java.util.List;
import mapper.BookMapper;
import model.Book;
import repository.BookRepository;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.getBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Can not find book by id:" + id)
        );
        return bookMapper.toDto(book);
    }
}
