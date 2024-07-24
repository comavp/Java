package ru.comavp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static ru.comavp.TestUtils.printlnWithThreadName;

public class TestBean implements InitializingBean, DisposableBean, TestBeanInterface, BeanNameAware, BeanFactoryAware,
        ApplicationContextAware {

    public TestBean() {
        printlnWithThreadName("TestBean object was created");
    }

    /*
    * Init methods
    * */

    @Override
    public void afterPropertiesSet() {
        printlnWithThreadName("Init method afterPropertiesSet() was called");
    }

    @PostConstruct
    public void initMethod() {
        printlnWithThreadName("Init method @PostConstruct was called");
    }

    public void anotherInitMethod() {
        printlnWithThreadName("Init method custom anotherInitMethod() was called");
    }

    /*
     * Destroy methods
     * */

    @Override
    public void destroy() throws Exception {
        printlnWithThreadName("Destroy method DisposableBean.destroy() was called");
    }

    @PreDestroy
    public void destroyMethod() {
        printlnWithThreadName("Destroy method @PreDestroy was called");
    }

    public void anotherDestroyMethod() {
        printlnWithThreadName("Destroy method custom anotherDestroyMethod() was called");
    }

    /*
    * Methods from Aware interfaces
    * */

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        printlnWithThreadName("Aware method setBeanFactory() was called. " + beanFactory.getClass().getName());
    }

    @Override
    public void setBeanName(String s) {
        printlnWithThreadName("Aware method setBeanName() was called. " + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        printlnWithThreadName("Aware method setApplicationContext() was called. " + applicationContext.getClass().getName());
    }
}
