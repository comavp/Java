package ru.comavp.javafxtutorial;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.comavp.javafxtutorial.application.JavaFxApp;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        Application.launch(JavaFxApp.class, args);
    }
}
