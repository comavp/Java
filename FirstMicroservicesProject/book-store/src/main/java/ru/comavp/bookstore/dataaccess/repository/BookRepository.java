package ru.comavp.bookstore.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import ru.comavp.bookstore.dataaccess.entity.BookEntity;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAllByAuthorContaining(String author);
}
