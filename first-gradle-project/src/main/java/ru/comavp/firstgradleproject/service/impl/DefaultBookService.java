package ru.comavp.firstgradleproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.comavp.firstgradleproject.dataacess.entity.BookEntity;
import ru.comavp.firstgradleproject.exceptions.BookNotFoundException;
import ru.comavp.firstgradleproject.dataacess.mappers.BookToEntityMapper;
import ru.comavp.firstgradleproject.dataacess.model.Book;
import ru.comavp.firstgradleproject.dataacess.repository.BookRepository;
import ru.comavp.firstgradleproject.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookToEntityMapper mapper;

    @Override
    public Book getBookById(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: id = " + id));

        return mapper.bookEntityToBook(bookEntity);
    }

    @Override
    public List<Book> getAllBooks() {
        Iterable<BookEntity> iterable = bookRepository.findAll();

        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : iterable) {
            books.add(mapper.bookEntityToBook(bookEntity));
        }

        return books;
    }

    @Override
    public void addBook(Book book) {
        BookEntity bookEntity = mapper.bookToBookEntity(book);
        bookRepository.save(bookEntity);
    }
}
