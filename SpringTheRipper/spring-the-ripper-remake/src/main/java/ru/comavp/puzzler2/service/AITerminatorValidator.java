package ru.comavp.puzzler2.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Order(6)
public class AITerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {
        System.out.println("AI Terminator Validator: Validating ...");
    }
}
