package ru.comavp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.comavp.MyCustomEventPublisher;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class TestContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MyCustomEventPublisher eventPublisher;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        printlnWithThreadName("ApplicationListener for ContextRefreshedEvent was called");
        eventPublisher.publishCustomEvent("Custom event was created in TestContextRefreshedListener");
        eventPublisher.publishAnotherCustomEvent("Custom event was created in TestContextRefreshedListener");
        eventPublisher.publishCustomGenericEvent("Custom event was created in TestContextRefreshedListener");
    }
}
