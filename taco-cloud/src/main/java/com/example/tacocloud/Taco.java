package com.example.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    @NotNull(message="You must choose at least 1 ingredient")
    @Size(min=1)
    private List<Ingredient> ingredients; // todo ошибка в случае, если не один ингредиент не был выбран не отображается
}
