package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.view.EmployeeListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
    @Query("select e.empId as empId, e.empName as empName, e.designation as designation, e.empstatus as empstatus from Employee e")
    public Page<EmployeeListView> findAllEmployeeListViews(Pageable pageable);
}
