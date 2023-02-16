package org.antwalk.ems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.antwalk.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
