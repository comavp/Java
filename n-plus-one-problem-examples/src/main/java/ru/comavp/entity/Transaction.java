package ru.comavp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
//@DiscriminatorValue("TRANSACTION")
public class Transaction extends AbstractEntity {

    @Column(name = "source_account")
    private String sourceAccount;

    @Column(name = "destination_account")
    private String destinationAccount;

    public Transaction() {
    }

    public Transaction(String internalName, LocalDateTime creationDate, LocalDateTime modifyDate, String sourceAccount, String destinationAccount) {
        super(internalName, creationDate, modifyDate);
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
