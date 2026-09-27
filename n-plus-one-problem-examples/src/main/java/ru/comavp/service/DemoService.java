package ru.comavp.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.comavp.entity.Payment;
import ru.comavp.entity.Transaction;
import ru.comavp.repository.PaymentRepository;
import ru.comavp.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DemoService implements CommandLineRunner {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;

    public DemoService(PaymentRepository paymentRepository, TransactionRepository transactionRepository) {
        this.paymentRepository = paymentRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) {
        Payment payment = new Payment(
                "payment-1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new BigDecimal("100.00")
        );
        paymentRepository.save(payment);

        Transaction transaction = new Transaction(
                "transaction-1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "ACC-001",
                "ACC-002"
        );
        transactionRepository.save(transaction);

        System.out.println("=== Запуск при старте приложения ===");
        System.out.println("=== Сохранён Payment: " + paymentRepository.findAll().size());
        System.out.println("=== Сохранён Transaction: " + transactionRepository.findAll().size());
        transactionRepository.findAll();
    }
}
