package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderClient {

    public String getOrderInfo() {
        log.info("Sending request to OrderClient");
        try {
            Thread.sleep(5_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Request to OrderClient successfully finished");
        return "Orders info";
    }
}
