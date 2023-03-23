//package org.antwalk.ems.repository;
//
//import java.util.List;
//import java.util.Optional;
//
////import org.antwalk.ems.model.Department;
//import org.antwalk.ems.view.EmployeeSelectionView;
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
//   @Query("SELECT COUNT(*) as count FROM Employee GROUP BY gender")
//   List<Integer> sexratio();
//   
//   @Query("SELECT SUM(ctc) FROM Employee GROUP BY department_dept_id")
//   List<Double> totalcost();
//
//   @Query("select e.empId as empId, e.empName as empName from Employee e where year_of_experience>5 ")
//   public List<EmployeeSelectionView> findAllHOD();
//
//}
