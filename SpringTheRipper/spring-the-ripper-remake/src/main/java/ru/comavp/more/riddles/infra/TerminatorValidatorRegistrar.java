package ru.comavp.more.riddles.infra;

import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TerminatorValidatorRegistrar implements BeanRegistrar {

    public static final String CONFIG_FILE = "terminator-validators.json";

    @SneakyThrows
    @Override
    public void register(BeanRegistry registry, Environment env) {
        ValidatorConfigDto config = loadConfiguration();
        config.getValidators().forEach(validator -> registerValidator(registry, validator));
    }

    private ValidatorConfigDto loadConfiguration() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(CONFIG_FILE);
        try (InputStream inputStream = resource.getInputStream()) {
            return mapper.readValue(inputStream, ValidatorConfigDto.class);
        }
    }

    @SneakyThrows
    private void registerValidator(BeanRegistry registry, ValidatorConfigDto.ValidatorDefinition validator) {
        Class<?> validatorClass = Class.forName(validator.getClassName());
        registry.registerBean(validator.getBeanName(), validatorClass, spec -> {
            spec.order(validator.getOrder());
            if (validator.getDescription() != null) {
                spec.description(validator.getDescription());
            }
            if (validator.isLazy()) {
                spec.lazyInit();
            }
            if ("prototype".equalsIgnoreCase(validator.getScope())) {
                spec.prototype();
            }
            if (validator.isPrimary()) {
                spec.primary();
            }
        });
        System.out.println("Registered bean: " + validator.getBeanName() +
                " [class=" + validator.getClassName() +
                ", order=" + validator.getOrder() +
                ", lazy=" + validator.isLazy() +
                ", scope=" + validator.getScope() + "]");
    }
}
