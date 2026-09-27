package ru.comavp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.comavp.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
