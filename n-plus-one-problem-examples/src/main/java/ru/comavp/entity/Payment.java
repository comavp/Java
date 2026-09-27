package ru.comavp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@DiscriminatorValue("PAYMENT")
public class Payment extends AbstractEntity {

    @Column
    private BigDecimal amount;

    public Payment() {
    }

    public Payment(String internalName, LocalDateTime creationDate, LocalDateTime modifyDate, BigDecimal amount) {
        super(internalName, creationDate, modifyDate);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
