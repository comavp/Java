package ru.comavp.bookstore.dataaccess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.bookstore.dataaccess.entity.BookEntity;
import ru.comavp.bookstore.dataaccess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToEntityMapper {

    BookEntity bookToBookEntity(Book book);
    Book bookEntityToBook(BookEntity bookEntity);
}
