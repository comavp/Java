package ru.comavp.datagenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.comavp.datagenerator.entity.Employee;

/**
 * @author Claude Desktop
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}