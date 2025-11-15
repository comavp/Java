package ru.comavp.transactionalexamples.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String data;

    public TestEntity(String data) {
        this.data = data;
    }
}
