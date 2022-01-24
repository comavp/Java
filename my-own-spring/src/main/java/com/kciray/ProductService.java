package com.kciray;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotations.Autowired;
import org.springframework.beans.factory.annotations.Resource;
import org.springframework.beans.factory.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ProductService implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    @Autowired
    private PromotionService promotionService;
    @Resource
    private PriceCalculatorService priceCalculatorService;

    private String beanName;
    private BeanFactory beanFactory;

    public PromotionService getPromotionService() {
        return this.promotionService;
    }

    public void setPromotionService(final PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    public PriceCalculatorService getPriceCalculatorService() { return this.priceCalculatorService; }

    public void setPriceCalculatorService(final PriceCalculatorService priceCalculatorService) {
        this.priceCalculatorService = priceCalculatorService;
    }

    @Override
    public void setBeanName(final String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }

    @Override
    public void setBeanFactory(final BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Тут происходит инициализация бина " + this.getBeanName());
    }

    @PostConstruct
    public void initBeforeAfterPropertiesSet() {
        System.out.println("Тут происходит инициализация бина " + this.getBeanName() + " с помощью @PostConstruct");
    }

    @Override
    public void destroy() {
        System.out.println("Тут происходит удаление бина " + this.getBeanName());
    }

    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("Тут происходит удаление бина " + this.getBeanName() + " с помощью @PreDestroy");
    }
}
