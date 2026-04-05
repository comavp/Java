package ru.comavp.puzzler2.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "terminator.model.dynamic.enabled", havingValue = "true")
public class DynamicTerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {

    }
}
