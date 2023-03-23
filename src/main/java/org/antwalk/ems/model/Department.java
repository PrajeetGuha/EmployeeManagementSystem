//package org.antwalk.ems.model;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table
//public class Department {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long deptId;
//
//    @Column(length = 255)
//    private String departmentName;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private Employee hod;
//
//    @OneToMany(mappedBy = "department")
//    @JsonIgnoreProperties("department")
//    private List<Employee> employees;
//
//    @OneToMany(mappedBy = "department")
//    @JsonIgnoreProperties("department")
//    private List<Team> teams;
//
//    public Department() {
//    }
//   
//   
//    public List<Team> getTeams() {
//		return teams;
//	}
//
//
//	public void setTeams(List<Team> teams) {
//		this.teams = teams;
//	}
//
//
//	public Department(Long deptId, String departmentName, Employee hod, List<Employee> employees, List<Team> teams) {
//		super();
//		this.deptId = deptId;
//		this.departmentName = departmentName;
//		this.hod = hod;
//		this.employees = employees;
//		this.teams = teams;
//	}
//	public Long getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(Long deptId) {
//        this.deptId = deptId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public Employee getHod() {
//        return hod;
//    }
//
//    public void setHod(Employee hod) {
//        this.hod = hod;
//    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//  
//}
