package ru.comavp.puzzler3;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;

@Component
public class Bpp implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (factory.getBeanDefinition(beanName).isPrototype()
                && Arrays.stream(ReflectionUtils.getDeclaredMethods(bean.getClass())) // по-хорошему нужно пробежаться по методам интерфейсов и классов-родителей
                .anyMatch(method -> method.isAnnotationPresent(PreDestroy.class))) { // по-хорошему нужно искать не только @PreDestroy, но и кастомные destroy методы
            throw new RuntimeException("PreDestroy annotation found on prototype");
        }
        return bean;
    }
}
