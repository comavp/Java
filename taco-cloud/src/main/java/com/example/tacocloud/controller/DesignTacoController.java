package com.example.tacocloud.controller;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Ingredient.Type;
import com.example.tacocloud.model.Taco;
import com.example.tacocloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(final Model model) {
        addIngredientsIntoDesignView(model);

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid final Taco taco, final Errors errors, final Model model) {
        if (errors.hasErrors()) {
            addIngredientsIntoDesignView(model);
            return "design";
        }

        // todo save the taco design
        log.info("Processing design: " + taco);

        return "redirect:/orders/current";
    }

    private void addIngredientsIntoDesignView(final Model model) {
        final List<Ingredient> allIngredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(allIngredients::add);

        Arrays.stream(Type.values()).forEach(type -> model.addAttribute(type.toString().toLowerCase(),
                allIngredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList())));
    }
}
