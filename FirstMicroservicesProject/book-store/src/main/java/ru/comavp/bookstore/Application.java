package ru.comavp.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.comavp.bookstore.dataaccess")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
