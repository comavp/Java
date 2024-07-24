package ru.comavp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import ru.comavp.conditions.ConditionOnProperty;

@Configuration
@PropertySource("classpath:application.yml")
public class TestConfig {

    @Bean(initMethod = "anotherInitMethod", destroyMethod = "anotherDestroyMethod")
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean(name = "applicationEventMulticaster")
    @Conditional(ConditionOnProperty.class)
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
