package com.kciray;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class Main {

    // todo вынести проверочные вызовы в тесты

    public static void main(String[] args)  {
        try {
            final ApplicationContext context = new ApplicationContext("com.kciray");

            final ProductService productService = (ProductService) context.getBean("productService");

            System.out.println(productService);
            System.out.println(productService.getPromotionService());
            System.out.println(productService.getPriceCalculatorService());
            System.out.println("Bean name: " + productService.getBeanName());
            System.out.println("Bean factory: " + productService.getBeanFactory());

            context.close();
        } catch (final Exception e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
