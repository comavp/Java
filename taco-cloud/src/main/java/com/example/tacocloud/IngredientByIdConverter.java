package com.example.tacocloud;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.tacocloud.controller.DesignTacoController.TEST_INGREDIENTS;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(final String typeId) {
        return TEST_INGREDIENTS.stream().filter(ingredient -> typeId.equals(ingredient.getId())).findFirst().get();
    }
}
