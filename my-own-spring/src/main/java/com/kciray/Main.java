package com.kciray;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class Main {

    // todo вынести проверочные вызовы в тесты

    public static void main(String[] args)  {
        try {
            final BeanFactory beanFactory = new BeanFactory();
            beanFactory.addPostProcessor(new CommonAnnotationBeanPostProcessor());
            beanFactory.addPostProcessor(new CustomPostProcessor());
            beanFactory.instantiate("com.kciray");
            beanFactory.populateProperties();
            beanFactory.injectBeanNames();
            beanFactory.injectBeanFactories();
            beanFactory.initializeBeans();

            final ProductService productService = (ProductService) beanFactory.getBean("productService");

            System.out.println(productService);
            System.out.println(productService.getPromotionService());
            System.out.println(productService.getPriceCalculatorService());
            System.out.println("Bean name: " + productService.getBeanName());
            System.out.println("Bean factory: " + productService.getBeanFactory());

            beanFactory.close();
        } catch (final Exception e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
