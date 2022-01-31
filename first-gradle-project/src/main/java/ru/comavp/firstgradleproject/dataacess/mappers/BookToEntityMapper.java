package ru.comavp.firstgradleproject.dataacess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.firstgradleproject.dataacess.entity.BookEntity;
import ru.comavp.firstgradleproject.dataacess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToEntityMapper {

    BookEntity bookToBookEntity(Book book);
    Book bookEntityToBook(BookEntity bookEntity);
}
