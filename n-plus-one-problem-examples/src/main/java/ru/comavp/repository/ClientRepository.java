package ru.comavp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.comavp.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c")
    @EntityGraph(attributePaths = {"orders"})
    List<Client> findAllUsingEntityGraph();
    
    // Дополнительные методы для решения проблемы N+1

    // Использование JOIN FETCH для загрузки клиентов с заказами одним запросом
    @Query("SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.orders")
    List<Client> findAllWithOrders();
    
    // Загрузка одного клиента с заказами
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.orders WHERE c.id = :id")
    Optional<Client> findByIdWithOrders(@Param("id") Long id);
}
