package com.kciray;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotations.Autowired;
import org.springframework.beans.factory.annotations.Resource;
import org.springframework.beans.factory.stereotype.Service;

@Service
public class ProductService implements BeanNameAware, BeanFactoryAware {

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
}
