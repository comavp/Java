package ru.comavp.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.comavp.events.MyCustomEvent;

import static ru.comavp.TestUtils.printlnWithThreadName;

@Component
public class CustomSpringEventListener implements ApplicationListener<MyCustomEvent> {

    @Override
    public void onApplicationEvent(MyCustomEvent event) {
        printlnWithThreadName("ApplicationListener for MyCustomEvent, message from event: " + event.getMessage());
    }
}
