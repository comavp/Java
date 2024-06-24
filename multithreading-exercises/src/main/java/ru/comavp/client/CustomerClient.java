package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerClient {

    public String getCustomersInfo() {
        log.info("Sending request to CustomerClient");
        try {
            Thread.sleep(5_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Request to CustomerClient successfully finished");
        return "Customers info";
    }
}
