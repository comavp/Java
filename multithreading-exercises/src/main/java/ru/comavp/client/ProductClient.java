package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductClient {

    public String getProductsInfo() {
        log.info("Sending request to ProductClient");
        try {
            Thread.sleep(5_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Request to ProductClient successfully finished");
        return "Products info";
    }
}
