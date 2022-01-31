package ru.comavp.firstgradleproject.dataacess.mappers;

import org.mapstruct.Mapper;
import ru.comavp.firstgradleproject.dataacess.dto.AddBookRequest;
import ru.comavp.firstgradleproject.dataacess.model.Book;

@Mapper(componentModel = "spring")
public interface BookToDtoMapper {

    Book mapAddBookRequestToBook(AddBookRequest addBookRequest);
}
