package ru.comavp.service;

import org.springframework.stereotype.Service;
import ru.comavp.aspect.LogExecutionTime;
import ru.comavp.dto.Company;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CompanyService {

    @LogExecutionTime
    public Company getCompany(String str) throws InterruptedException {
        if ("error".equals(str)) throw new RuntimeException("Bad property was sent");

        Thread.sleep(100 * getRandomInt(1, 9));
        return new Company(UUID.randomUUID(), "testName_" + getRandomInt(0, 10000),
                "testDescription_" + getRandomInt(0, 10000));
    }

    private Integer getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
