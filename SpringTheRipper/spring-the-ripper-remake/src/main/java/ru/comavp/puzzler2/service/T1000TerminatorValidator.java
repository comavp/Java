package ru.comavp.puzzler2.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "terminator.model.t1000.enabled", havingValue = "true")
public class T1000TerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {

    }
}
