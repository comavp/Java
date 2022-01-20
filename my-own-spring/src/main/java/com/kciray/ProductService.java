package com.kciray;

import org.springframework.beans.factory.stereotype.Service;

@Service
public class ProductService {

    private PromotionService promotionService;

    public PromotionService getPromotionService() {
        return this.promotionService;
    }

    public void setPromotionService(final PromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
