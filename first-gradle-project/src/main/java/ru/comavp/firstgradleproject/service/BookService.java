package ru.comavp.firstgradleproject.service;

import ru.comavp.firstgradleproject.dataacess.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addBook(Book book);
}
