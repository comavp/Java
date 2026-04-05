package ru.comavp.puzzler2.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Skynet {

//    /*
//    * puzzler 2
//    * */
//
//    private final TerminatorValidator terminatorValidator;
//
//    public Skynet(TerminatorValidator terminatorValidator) {
//        this.terminatorValidator = terminatorValidator;
//        System.out.println("=== Skynet initialized with " + terminatorValidator.getClass().getSimpleName() + " ===");
//    }

    /*
    * puzzler What will be the content of List
    * */

//    private final List<TerminatorValidator> terminatorValidators;
//
//    public Skynet(List<TerminatorValidator> terminatorValidators) {
//        this.terminatorValidators = terminatorValidators;
//        System.out.println("=== Skynet initialized with " + terminatorValidators.size() + " validators ===");
//        for (int i = 0; i < terminatorValidators.size(); i++) {
//            TerminatorValidator validator = terminatorValidators.get(i);
//            System.out.println((i + 1) + ". " + validator.getClass().getSimpleName());
//        }
//    }

    private final Map<String, TerminatorValidator> terminatorValidators;

    public Skynet( Map<String, TerminatorValidator> terminatorValidators) {
        this.terminatorValidators = terminatorValidators;
        System.out.println("=== Skynet initialized with " + terminatorValidators.size() + " validators ===");
        AtomicInteger cnt = new AtomicInteger(0);
        terminatorValidators.forEach((validatorName, validator) -> {
            System.out.println((cnt.incrementAndGet()) + ". " + validator.getClass().getSimpleName());
        });
    }

    public void validateTerminator(Terminator terminator) {
        terminatorValidators.values().forEach(validator -> validator.validate(terminator));
    }
}
