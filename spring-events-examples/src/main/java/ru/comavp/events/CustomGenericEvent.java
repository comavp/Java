package ru.comavp.events;

import org.springframework.context.ApplicationEvent;

public class CustomGenericEvent<T> extends ApplicationEvent {

    T message;

    public CustomGenericEvent(Object source, T message) {
        super(source);
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
}
