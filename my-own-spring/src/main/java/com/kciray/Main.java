package com.kciray;

import org.springframework.beans.factory.BeanFactory;

public class Main {

    // todo вынести проверочные вызовы в тесты

    public static void main(String[] args)  {
        try {
            final BeanFactory beanFactory = new BeanFactory();
            beanFactory.instantiate("com.kciray");
            beanFactory.populateProperties();
            beanFactory.injectBeanNames();
            beanFactory.injectBeanFactories();
            final ProductService productService = (ProductService) beanFactory.getBean("productService");
            System.out.println(productService);
            System.out.println(productService.getPromotionService());
            System.out.println(productService.getPriceCalculatorService());
            System.out.println("Bean name: " + productService.getBeanName());
            System.out.println("All beans: " + productService.getBeanFactory());
        } catch (final Exception e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
