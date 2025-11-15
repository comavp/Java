package ru.comavp.datagenerator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.comavp.datagenerator.entity.Employee;
import ru.comavp.datagenerator.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Claude Desktop
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGeneratorService implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Value("${data.generator.records-count:1000}")
    private int recordsCount;

    @Value("${data.generator.batch-size:500}")
    private int batchSize;

    private final Random random = new Random();

    private static final String[] FIRST_NAMES = {
            "Александр", "Дмитрий", "Максим", "Сергей", "Андрей",
            "Алексей", "Артем", "Илья", "Кирилл", "Михаил",
            "Иван", "Никита", "Павел", "Владимир", "Роман",
            "Елена", "Ольга", "Анна", "Мария", "Наталья",
            "Ирина", "Татьяна", "Юлия", "Светлана", "Екатерина"
    };

    private static final String[] LAST_NAMES = {
            "Иванов", "Петров", "Сидоров", "Смирнов", "Козлов",
            "Новиков", "Морозов", "Волков", "Соколов", "Лебедев",
            "Семенов", "Егоров", "Павлов", "Федоров", "Михайлов"
    };

    private static final String[] SEXES = {"М", "Ж"};

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Начало генерации {} записей", recordsCount);

        long startTime = System.currentTimeMillis();

        List<Employee> batch = new ArrayList<>();

        for (int i = 0; i < recordsCount; i++) {
            batch.add(generateRandomEmployee());

            if (batch.size() >= batchSize) {
                employeeRepository.saveAll(batch);
                batch.clear();
                log.info("Сохранено {} записей из {}", i + 1, recordsCount);
            }
        }

        // Сохраняем оставшиеся записи
        if (!batch.isEmpty()) {
            employeeRepository.saveAll(batch);
        }

        long endTime = System.currentTimeMillis();
        log.info("Генерация завершена. Создано {} записей за {} мс",
                recordsCount, endTime - startTime);
    }

    private Employee generateRandomEmployee() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        return Employee.builder()
                .name(firstName + " " + lastName)
                .salary(generateRandomSalary())
                .age(generateRandomAge())
                .sex(SEXES[random.nextInt(SEXES.length)])
                .createdAt(generateRandomDateTime())
                .build();
    }

    private Long generateRandomSalary() {
        // Зарплата от 30,000 до 300,000
        return 30000L + (long) (random.nextDouble() * 270000);
    }

    private Integer generateRandomAge() {
        // Возраст от 18 до 65
        return 18 + random.nextInt(48);
    }

    private LocalDateTime generateRandomDateTime() {
        // Даты за последние 2 года
        LocalDateTime now = LocalDateTime.now();
        int daysBack = random.nextInt(730); // 2 года
        return now.minusDays(daysBack)
                .minusHours(random.nextInt(24))
                .minusMinutes(random.nextInt(60));
    }
}