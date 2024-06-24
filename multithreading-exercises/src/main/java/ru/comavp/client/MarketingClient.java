package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarketingClient {

    public String getMarketingInfo() {
        log.info("Sending request to MarketingClient");
        try {
            Thread.sleep(5_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Request to MarketingClient successfully finished");
        return "Marketing info";
    }
}
