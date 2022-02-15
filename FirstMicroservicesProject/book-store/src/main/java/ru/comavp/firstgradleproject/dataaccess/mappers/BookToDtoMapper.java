package ru.comavp.firstgradleproject.dataaccess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.firstgradleproject.dataaccess.dto.AddBookRequest;
import ru.comavp.firstgradleproject.dataaccess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToDtoMapper {

    Book mapAddBookRequestToBook(AddBookRequest addBookRequest);
}
