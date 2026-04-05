package ru.comavp.puzzler2.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "terminator.model.ineedyoucloses.enabled", havingValue = "true")
public class INeedYourClothesTerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {
        System.out.println("Legacy Terminator Validator since 1984");
    }
}
