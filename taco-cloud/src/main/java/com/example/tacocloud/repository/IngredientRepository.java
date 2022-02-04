package com.example.tacocloud.repository;

import com.example.tacocloud.model.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(final String id);
    Ingredient save(final Ingredient ingredient);
}