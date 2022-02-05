package ru.comavp.firstgradleproject.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import ru.comavp.firstgradleproject.dataaccess.entity.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
