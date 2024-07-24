package ru.comavp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.comavp");
        context.getBean(TestBean.class);
        context.stop();
        context.start();
        context.close();
    }
}
