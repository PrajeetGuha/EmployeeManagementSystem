package org.antwalk.ems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.antwalk.ems.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
