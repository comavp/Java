package com.example.tacocloud.model;

import com.example.tacocloud.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(final String typeId) {
        final List<Ingredient> allIngredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(allIngredients::add);
        return allIngredients.stream().filter(ingredient -> typeId.equals(ingredient.getId())).findFirst().get();
    }
}
