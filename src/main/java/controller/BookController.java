package controller;

import dto.book.BookDto;
import dto.book.CreateBookRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mapper.BookMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all books", description = "Get a list of all available books")
    public List<BookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get a book by id", description = "Gets a book by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new book", description = "Creates a new book")
    public BookDto createBook(@Valid @RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id in database")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a book by id", description = "Update a book by id in database")
    public void updateBook(@PathVariable Long id,
                           @Valid @RequestBody CreateBookRequestDto requestDto) {
        bookService.update(id, bookMapper.toModel(requestDto));
    }
}
