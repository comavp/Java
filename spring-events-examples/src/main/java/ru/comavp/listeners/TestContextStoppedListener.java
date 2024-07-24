package ru.comavp.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class TestContextStoppedListener implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        printlnWithThreadName("ApplicationListener for ContextStoppedEvent was called");
    }
}
