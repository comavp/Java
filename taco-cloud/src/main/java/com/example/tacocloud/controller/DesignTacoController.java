package com.example.tacocloud.controller;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Ingredient.Type;
import com.example.tacocloud.model.Order;
import com.example.tacocloud.model.Taco;
import com.example.tacocloud.repository.IngredientRepository;
import com.example.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(final IngredientRepository ingredientRepository, final TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(final Model model) {
        addIngredientsIntoDesignView(model);

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid final Taco taco, @ModelAttribute final Order order,
                                final Errors errors, final Model model) {
        if (errors.hasErrors()) {
            addIngredientsIntoDesignView(model);
            return "design";
        }

        log.info("Processing design: " + taco);
        final Taco saved = tacoRepository.save(taco);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    private void addIngredientsIntoDesignView(final Model model) {
        final List<Ingredient> allIngredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(allIngredients::add);

        Arrays.stream(Type.values()).forEach(type -> model.addAttribute(type.toString().toLowerCase(),
                allIngredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList())));
    }
}
