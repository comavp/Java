package ru.comavp.more.riddles.infra;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidatorConfigDto {

    private List<ValidatorDefinition> validators;

    @Data
    public static class ValidatorDefinition {
        private String beanName;
        private String className;
        private int order;
        boolean lazy;
        private String scope;
        private String description;
        private boolean primary;
    }
}
