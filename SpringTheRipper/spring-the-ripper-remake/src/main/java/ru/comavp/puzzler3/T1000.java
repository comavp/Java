package ru.comavp.puzzler3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.comavp.puzzler2.service.Terminator;

@Component
@Scope("singleton")
public class T1000 implements Terminator {

    @PostConstruct
    public void init() {
        System.out.println("Have you seen this boy?");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("bye bye teletubbies");
    }
}
