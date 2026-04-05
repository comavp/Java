package ru.comavp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.comavp.puzzler3.T800;

@SpringBootApplication
@ComponentScan(basePackages = "ru.comavp.puzzler3")
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, "--terminator.main.mission=Save and release all hostages");
        T800 t800 = (T800) context.getBean("t800"); // for puzzler 3
        System.out.println("\n=== Application started successfully ===");
        context.close();
    }
}
