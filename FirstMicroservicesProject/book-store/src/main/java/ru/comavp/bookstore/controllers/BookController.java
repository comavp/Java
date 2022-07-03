package ru.comavp.bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.comavp.bookstore.dataaccess.dto.AddBookRequest;
import ru.comavp.bookstore.dataaccess.mappers.BookToDtoMapper;
import ru.comavp.bookstore.dataaccess.model.Book;
import ru.comavp.bookstore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookToDtoMapper mapper;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author) {
        if (author != null) {
            return bookService.findByAuthor(author);
        }
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody AddBookRequest request) {
        bookService.addBook(mapper.mapAddBookRequestToBook(request));
    }
}
