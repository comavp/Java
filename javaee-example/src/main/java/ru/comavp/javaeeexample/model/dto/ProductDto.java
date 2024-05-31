package ru.comavp.javaeeexample.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDto {

    private long id;
    private String name;
    private String description;
    private double price;
}
