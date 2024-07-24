package ru.comavp.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.comavp.TestBean;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            printlnWithThreadName("BeanPostProcessor postProcessBeforeInitialization was called");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof  TestBean) {
            printlnWithThreadName("BeanPostProcessor postProcessAfterInitialization was called");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
