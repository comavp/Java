package com.kciray;

import org.springframework.beans.factory.BeanFactory;

public class Main {

    public static void main(String[] args)  {
        try {
            final BeanFactory beanFactory = new BeanFactory();
            beanFactory.instantiate("com.kciray");
            final ProductService productService = (ProductService) beanFactory.getBean("productService");
            System.out.println(productService);
        } catch (final Exception e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
