package service;

import dto.book.BookDto;
import dto.book.BookDtoWithoutCategoriesIds;
import dto.book.CreateBookRequestDto;
import exception.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mapper.BookMapper;
import model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BookRepository;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

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
    public List<BookDtoWithoutCategoriesIds> getBooksByCategoryId(Long id,  Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategoriesIds)
                .toList();
    }
}
