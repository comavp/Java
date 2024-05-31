package ru.comavp.javaeeexample.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.comavp.javaeeexample.model.entity.Product;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class ProductRepository {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    public Product create(Product product) {
        logger.info("Creating product " + product.getName());
        entityManager.persist(product);
        return product;
    }

    public List<Product> findAll() {
        logger.info("Getting all products");
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Optional<Product> findById(Long id) {
        logger.info("Getting product by id " + id);
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public void delete(Long id) {
        logger.info("Deleting product by id " + id);
        var product = findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));
        entityManager.remove(product);
    }

    public Product update(Product product) {
        logger.info("Updating product " + product.getName());
        return entityManager.merge(product);
    }
}
