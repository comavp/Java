package ru.comavp.puzzler3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.comavp.puzzler2.service.Terminator;

@Component
@Scope("prototype")
public class T800 implements Terminator {

    @PostConstruct
    public void init() {
        System.out.println("I need your clothes, your boots and your motorcycle");
    }

    //@PreDestroy
    public void destroy() {
        System.out.println("I'll be back, maybe ...");
    }
}
