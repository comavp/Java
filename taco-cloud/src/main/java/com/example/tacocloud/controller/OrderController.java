package com.example.tacocloud.controller;

import com.example.tacocloud.model.Order;
import com.example.tacocloud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(final Model model) {
        model.addAttribute("order", new Order());

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid final Order order, final Errors errors, final SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: " + order);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
