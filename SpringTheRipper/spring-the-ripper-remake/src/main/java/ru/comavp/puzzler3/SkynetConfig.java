package ru.comavp.puzzler3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkynetConfig {

    @Bean(destroyMethod = "toString")
    public T1000 t1000() {
        return new T1000();
    }
}
