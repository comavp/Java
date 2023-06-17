package ru.comavp.customerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.comavp.customerservice.model.Book;
import ru.comavp.customerservice.service.BookClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    public final BookClient bookClient;

    public CustomerController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookClient.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> findById(@PathVariable Integer id) {
        return bookClient.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Book book) {
        bookClient.create(book);
    }

    @PutMapping("/books/{id}")
    public void update(@PathVariable Integer id, @RequestBody Book modifiedBook) {
        bookClient.update(id, modifiedBook);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Integer id) {
        bookClient.delete(id);
    }
}
