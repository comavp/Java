package ru.comavp.more.riddles.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Environment post-processor that transforms the terminator.main.mission property
 * by extracting the first letter of each word to create an acronym.
 * Special case: if the acronym is "Sarah", it becomes "Sarah Connor".
 */
public class TerminatorAcronymEpp implements EnvironmentPostProcessor {

    public static final String PROPERTY_NAME = "terminator.main.mission";
    public static final String PROPERTY_SOURCE_NAME = "terminatorPropertySource";
    public static final String SARAH_ACRONYM = "Sarah";
    public static final String SARAH_CONNOR = "Sarah Connor";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String mission = environment.getProperty(PROPERTY_NAME);
        if (StringUtils.isEmpty(mission)) {
            return;
        }
        String acronym = buildAcronym(mission);
        String transformValue = applySpecialRules(acronym);
        overrideProperty(environment, transformValue);
    }

    private String buildAcronym(String mission) {
        String[] words = mission.split("\\s+");
        StringBuilder acronym = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                acronym.append(word.charAt(0));
            }
        }
        return acronym.toString();
    }

    private String applySpecialRules(String acronym) {
        return acronym.equals(SARAH_ACRONYM) ? SARAH_CONNOR : acronym;
    }

    private void overrideProperty(ConfigurableEnvironment environment, String value) {
        MapPropertySource propertySource = new MapPropertySource(
                PROPERTY_SOURCE_NAME,
                Map.of(PROPERTY_NAME, value));
        environment.getPropertySources().addFirst(propertySource);
    }
}
