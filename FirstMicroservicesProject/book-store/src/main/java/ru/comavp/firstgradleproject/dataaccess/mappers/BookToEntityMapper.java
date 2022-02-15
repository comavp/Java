package ru.comavp.firstgradleproject.dataaccess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.firstgradleproject.dataaccess.entity.BookEntity;
import ru.comavp.firstgradleproject.dataaccess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToEntityMapper {

    BookEntity bookToBookEntity(Book book);
    Book bookEntityToBook(BookEntity bookEntity);
}
