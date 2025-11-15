package ru.comavp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.comavp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
