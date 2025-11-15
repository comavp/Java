package ru.comavp.transactionalexamples.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.comavp.transactionalexamples.model.TestEntity;
import ru.comavp.transactionalexamples.repository.TestRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestNotificationService notificationService;

    @Transactional
    public void updateEntity(Integer entityId, String newData) {
        System.out.println("[" + Thread.currentThread().getName() + "] transactionFrom updateEntity() started");
        TestEntity oldEntity = testRepository.findById(entityId).get();
        oldEntity.setData(oldEntity.getData() + "_" + newData);
        testRepository.save(oldEntity);
        notificationService.notify(oldEntity + " was successfully saved");
        System.out.println("[" + Thread.currentThread().getName() + "] transactionFrom updateEntity() finished");
    }

    @Transactional
    public void addEntity(String data) {
        System.out.println("[" + Thread.currentThread().getName() + "] transactionFrom addEntity(" + data + ") started");
        if (data.equals("firstTry")) {
            throw new RuntimeException("my test exception");
        }
        TestEntity createdEntity = testRepository.save(new TestEntity(data));
        notificationService.notify(createdEntity + " was successfully created");
        notifyFromSameClass(createdEntity + " was successfully created");
        System.out.println("[" + Thread.currentThread().getName() + "] transactionFrom addEntity(" + data + ") finished");
    }

    private void notifyFromSameClass(String data) {
        if (data.contains("thirdTry")) {
            throw new RuntimeException("my test exception");
        }

        notificationService.notify(data + " extended with notify() from TestService class");
    }
}
