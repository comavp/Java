package ru.comavp.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.comavp.events.CustomGenericEvent;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class ListenerForCustomGenericEvents {

    @EventListener
    public void handleCustomEvent(CustomGenericEvent<String> event) {
        printlnWithThreadName("ApplicationListener for CustomGenericEvent<String>, message from event: " + event.getMessage());
    }
}
