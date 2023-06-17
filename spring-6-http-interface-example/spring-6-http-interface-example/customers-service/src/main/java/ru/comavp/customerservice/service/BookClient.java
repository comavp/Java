package ru.comavp.customerservice.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.comavp.customerservice.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookClient {

    @GetExchange("/books")
    List<Book> findAll();

    @GetExchange("/books/{id}")
    Optional<Book> findById(@PathVariable Integer id);

    @PostExchange("/books")
    void create(@RequestBody Book book);

    @PutExchange("/books/{id}")
    void update(@PathVariable Integer id, @RequestBody Book modifiedBook);

    @DeleteExchange("/books/{id}")
    void delete(@PathVariable Integer id);
}
