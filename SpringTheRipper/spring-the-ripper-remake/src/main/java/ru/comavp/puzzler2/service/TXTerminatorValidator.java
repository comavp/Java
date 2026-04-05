package ru.comavp.puzzler2.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "terminator.model.tx.enabled", havingValue = "true")
public class TXTerminatorValidator implements TerminatorValidator {

    @Override
    public void validate(Terminator terminator) {

    }
}
