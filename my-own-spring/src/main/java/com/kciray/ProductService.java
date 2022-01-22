package com.kciray;

import org.springframework.beans.factory.annotations.Autowired;
import org.springframework.beans.factory.annotations.Resource;
import org.springframework.beans.factory.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private PromotionService promotionService;
    @Resource
    private PriceCalculatorService priceCalculatorService;

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
}
