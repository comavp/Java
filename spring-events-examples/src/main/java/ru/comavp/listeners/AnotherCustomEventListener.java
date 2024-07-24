package ru.comavp.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.comavp.events.AnotherCustomEvent;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class AnotherCustomEventListener {

    @EventListener
    public void handleCustomEvent(AnotherCustomEvent event) {
        printlnWithThreadName("ApplicationListener for AnotherCustomEvent, message from event: " + event.getMessage());
    }
}
