package com.example.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.application.dto.book.BookDto;
import com.example.application.dto.book.CreateBookRequestDto;
import com.example.application.mapper.BookMapper;
import com.example.application.model.Book;
import com.example.application.repository.BookRepository;
import com.example.application.service.book.BookServiceImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    @DisplayName("Check save() method with correct requestDto")
    void saveBook_ValidBookRequest_ReturnBookDto() {

        CreateBookRequestDto testRequestDto = new CreateBookRequestDto();
        testRequestDto.setTitle("The Witcher");
        testRequestDto.setAuthor("Andrzej Sapkowski");
        testRequestDto.setPrice(BigDecimal.valueOf(20));
        testRequestDto.setIsbn("7583457647");

        Book testBook = new Book();
        testBook.setTitle(testRequestDto.getTitle());
        testBook.setAuthor(testRequestDto.getAuthor());
        testBook.setPrice(testRequestDto.getPrice());
        testBook.setIsbn(testRequestDto.getIsbn());

        BookDto testBookDto = new BookDto();
        testBookDto.setTitle(testBook.getTitle());
        testBookDto.setAuthor(testBook.getAuthor());
        testBookDto.setIsbn(testBook.getIsbn());
        testBookDto.setPrice(testBook.getPrice());

        when(bookMapper.toModel(testRequestDto)).thenReturn(testBook);
        when(bookRepository.save(testBook)).thenReturn(testBook);
        when(bookMapper.toDto(testBook)).thenReturn(testBookDto);

        BookDto actual = bookServiceImpl.save(testRequestDto);
        assertNotNull(actual);
        assertEquals(testBookDto, actual);
        verify(bookRepository, times(1)).save(testBook);
    }
}
