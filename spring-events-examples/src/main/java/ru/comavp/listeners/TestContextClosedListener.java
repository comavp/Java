package ru.comavp.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class TestContextClosedListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        printlnWithThreadName("ApplicationListener for ContextClosedEvent was called");
    }
}
