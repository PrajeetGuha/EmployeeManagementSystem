package org.antwalk.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.EmployeeSelectionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
    @Query("select e.empId as empId, e.empName as empName, e.workEmail as workEmail, e.designation as designation, e.empstatus as empstatus from Employee e")
    public Page<EmployeeListView> findAllEmployeeListViews(Pageable pageable);

    @Query("select e.empId as empId, e.empName as empName from Employee e")
    public List<EmployeeSelectionView> findAllEmployeeNames();

    @Query("select e.empId as empId, e.empName as empName, e.workEmail as workEmail, e.designation as designation, e.empstatus as empstatus from Employee e where e.empName like :search%")
    public Page<EmployeeListView> findAllEmployeeListViewsWithSearch(Pageable pageable, String search);

    @Query("select e from Employee e where e.empName like :search%")
    public Page<Employee> findAllBySearch(Pageable pageable, String search);

    @Query("select count(*) from Employee e where e.empName like :search%")
    public int countBySearch(String search);

    @Transactional
    @Modifying
    @Query("update Employee e set e.empstatus = 'inactive' where empId = :empId")
    public void deactivateEmpById(Long empId);

    @Transactional
    @Modifying
    @Query("update Employee e set e.empstatus = 'active' where empId = :empId")
    public void activateEmpById(Long empId);

    @Query("select workEmail from Employee e where empId = :empId")
    public String getWorkEmailByEmpId(Long empId);

    @Query("select empName from Employee e where empId = :empId")
    public String getEmpNameByEmpId(Long empId);

}
