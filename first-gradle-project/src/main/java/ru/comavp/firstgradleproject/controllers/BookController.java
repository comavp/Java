package ru.comavp.firstgradleproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.comavp.firstgradleproject.dataacess.dto.AddBookRequest;
import ru.comavp.firstgradleproject.dataacess.mappers.BookToDtoMapper;
import ru.comavp.firstgradleproject.dataacess.model.Book;
import ru.comavp.firstgradleproject.service.BookService;

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
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody AddBookRequest request) {
        bookService.addBook(mapper.mapAddBookRequestToBook(request));
    }
}
