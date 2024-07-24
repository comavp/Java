package ru.comavp.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class TestContextStartedListener implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        printlnWithThreadName("ApplicationListener for ContextStartedEvent was called");
    }
}
