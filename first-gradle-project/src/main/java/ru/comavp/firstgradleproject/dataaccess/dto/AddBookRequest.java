package ru.comavp.firstgradleproject.dataaccess.dto;

import lombok.Data;

@Data
public class AddBookRequest {

    private String author;
    private String title;
    private Double price;
}
