package ru.comavp.firstgradleproject.dataacess.repository;

import org.springframework.data.repository.CrudRepository;
import ru.comavp.firstgradleproject.dataacess.entity.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
