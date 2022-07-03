package ru.comavp.bookstore.dataaccess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.bookstore.dataaccess.dto.AddBookRequest;
import ru.comavp.bookstore.dataaccess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToDtoMapper {

    Book mapAddBookRequestToBook(AddBookRequest addBookRequest);
}
