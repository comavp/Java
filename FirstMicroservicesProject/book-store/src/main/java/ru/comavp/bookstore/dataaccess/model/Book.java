package ru.comavp.bookstore.dataaccess.model;

import lombok.Value;

@Value
public class Book {
    Long id;
    String author;
    String title;
    Double price;
}
