package ru.comavp.transactionalexamples.service;

import org.springframework.stereotype.Service;

@Service
public class TestNotificationService {

    public void notify(String data) {
        if (data.contains("second")) {
            throw new RuntimeException("my test exception");
        }

        System.out.println("[" + Thread.currentThread().getName() + "] notification with data: " + data + " was sent");
    }
}
