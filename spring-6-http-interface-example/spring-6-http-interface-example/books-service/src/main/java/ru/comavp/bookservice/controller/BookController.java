package ru.comavp.bookservice.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.comavp.bookservice.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    final List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.addAll(Arrays.asList(
                new Book(1, "First", "First description"),
                new Book(2, "Second", "Second description"),
                new Book(3, "Third", "Third description")
        ));
    }

    @GetMapping
    public List<Book> findAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Integer id) {
        return books.stream().filter(book -> book.id().equals(id)).findFirst();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Book book) {
        books.add(book);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Book modifiedBook) {
        Optional<Book> existingBook = books.stream().filter(book -> book.id().equals(id)).findFirst();
        existingBook.ifPresent(item -> books.set(books.indexOf(item), modifiedBook));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Book> existingBook = books.stream().filter(book -> book.id().equals(id)).findFirst();
        existingBook.ifPresent(item -> books.remove(books.indexOf(item)));
    }
}
