package ru.comavp.puzzler2.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import ru.comavp.more.riddles.infra.TerminatorValidatorRegistrar;
import ru.comavp.puzzler2.service.AITerminatorValidator;
import ru.comavp.puzzler2.service.Rev9TerminatorValidator;
import ru.comavp.puzzler2.service.TerminatorValidator;

import java.util.List;

@Configuration
@ImportResource("classpath:puzzlers4/puzzler2-context.xml")
@Import(TerminatorValidatorRegistrar.class)
public class SkynetConfig {

    @Value("${terminator.main.mission}")
    private String mission;

    @PostConstruct
    public void init() {
        System.out.println("Mission: " + mission);
    }

    @Bean
    @Scope("singleton")
    @Order(4)
    public AITerminatorValidator aITerminatorValidator() {
        return new AITerminatorValidator();
    }


    /*
    * for puzzler What will be the content of List
    * */
    //@Bean
    public List<TerminatorValidator> terminatorValidators() {
        return List.of(new Rev9TerminatorValidator());
    }
}
