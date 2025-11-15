package ru.comavp.transactionalexamples.repository;

import org.springframework.data.repository.CrudRepository;
import ru.comavp.transactionalexamples.model.TestEntity;

public interface TestRepository extends CrudRepository<TestEntity, Integer> {
}
