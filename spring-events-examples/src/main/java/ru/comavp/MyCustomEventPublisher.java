package ru.comavp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.comavp.events.AnotherCustomEvent;
import ru.comavp.events.CustomGenericEvent;
import ru.comavp.events.MyCustomEvent;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class MyCustomEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishCustomEvent(String message) {
        printlnWithThreadName("ApplicationEventPublisher publishCustomEvent() was called");
        eventPublisher.publishEvent(new MyCustomEvent(this, message));
    }

    public void publishAnotherCustomEvent(String message) {
        printlnWithThreadName("ApplicationEventPublisher publishCustomEvent() was called");
        eventPublisher.publishEvent(new AnotherCustomEvent(message));
    }

    public void publishCustomGenericEvent(String message) {
        printlnWithThreadName("ApplicationEventPublisher publishCustomEvent() was called");
        eventPublisher.publishEvent(new CustomGenericEvent<>(this, message));
    }
}
