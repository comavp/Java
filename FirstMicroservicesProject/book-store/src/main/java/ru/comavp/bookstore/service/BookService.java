package ru.comavp.bookstore.service;

import ru.comavp.bookstore.dataaccess.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addBook(Book book);
    List<Book> findByAuthor(String author);
}
