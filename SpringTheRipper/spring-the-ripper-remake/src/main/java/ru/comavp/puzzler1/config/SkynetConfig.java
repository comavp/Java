package ru.comavp.puzzler1.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkynetConfig {

    @Value("${terminator.main.mission}")
    private String mission;

    @PostConstruct
    public void init() {
        System.out.println("Terminator Main Mission: " + mission);
    }
}
