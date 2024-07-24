package ru.comavp;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static ru.comavp.TestUtils.printlnWithThreadName;

public interface TestBeanInterface {

    @PostConstruct
    default void initMethodFromInterface() {
        printlnWithThreadName("Init method @PostConstruct from interface was called");
    }

    @PreDestroy
    default void destroyMethodFromInterface() {
        printlnWithThreadName("Destroy method @PreDestroy from interface was called");
    }
}
