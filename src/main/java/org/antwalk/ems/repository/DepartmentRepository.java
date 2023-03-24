//package org.antwalk.ems.repository;
//
//import java.util.List;
//import java.util.Optional;
//

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface DepartmentRepository extends JpaRepository<Department,Long> {
//
//    @Query("select d.departmentName from Department d order by d.departmentName")
//    List<String> findAllDepartments();
//    
//    public Optional<Department> findByDepartmentName(String departmentName);
//    
//   @Query("SELECT COUNT(*) as employeecount FROM Employee  GROUP BY department_dept_id")
//   List<Integer> findemployeecount();
//   

//}
