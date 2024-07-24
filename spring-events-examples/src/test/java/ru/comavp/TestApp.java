package ru.comavp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {

    @Test
    public void testMethod() {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.comavp");
        context.getBean(TestBean.class);
    }
}
