package ru.comavp.puzzler2.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "terminator.model.rev9.enabled", havingValue = "true")
public class Rev9TerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {

    }
}
