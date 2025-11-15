package ru.comavp.transactionalexamples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.comavp.transactionalexamples.repository.TestRepository;
import ru.comavp.transactionalexamples.service.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
class TestServiceTest {

    @Autowired
    private TestService testService;
    @Autowired
    private TestRepository testRepository;

    @BeforeEach
    void beforeEach() {
        testRepository.deleteAll();
    }

    @Test
    void testMethod() {
        assertThrows(Exception.class, () -> testService.addEntity("firstTry"));
        assertEquals(0, testRepository.count());
        assertThrows(RuntimeException.class, () -> testService.addEntity("secondTry"));
        assertEquals(0, testRepository.count());
        assertThrows(RuntimeException.class, () -> testService.addEntity("thirdTry"));
        assertEquals(0, testRepository.count());
        testService.addEntity("fourthTry");
        assertEquals(1, testRepository.count());
    }
}