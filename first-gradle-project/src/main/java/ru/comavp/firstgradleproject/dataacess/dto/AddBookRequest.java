package ru.comavp.firstgradleproject.dataacess.dto;

import lombok.Data;

@Data
public class AddBookRequest {

    private String author;
    private String title;
    private Double price;
}
