package com.kciray;

import org.springframework.beans.factory.stereotype.Service;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@Service
public class PromotionService implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(">> ContextClosed EVENT");
    }
}
