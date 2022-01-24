package com.kciray;

import org.springframework.beans.factory.config.BeanPostProcessor;

// todo можно сделать этот класс бином
public class CustomPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("---CustomBeanPostProcessor: beforeInitialization for " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("---CustomBeanPostProcessor: afterInitialization for " + beanName);
        return bean;
    }
}
