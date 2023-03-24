package org.antwalk.ems.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class NewEmployeeDTO {
    
    private String name;
    private String email;
    private String gender;
    private String designation;
    private String department;
    private String gradeLevel;
    private Date doj;
    private String emptype;
    private String username;
    private String password;

    private Integer yearOfExperience;

    public NewEmployeeDTO() {
    }
   
    public Integer getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(Integer yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public NewEmployeeDTO(String name, String email, String gender, String designation, String department,
			String gradeLevel, Date doj, String emptype, String username, String password, Integer yearOfExperience) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.designation = designation;
		this.department = department;
		this.gradeLevel = gradeLevel;
		this.doj = doj;
		this.emptype = emptype;
		this.username = username;
		this.password = password;
		this.yearOfExperience = yearOfExperience;
	}

	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getGradeLevel() {
        return gradeLevel;
    }
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    public Date getDoj() {
        return doj;
    }
    public void setDoj(Date doj) {
        this.doj = doj;
    }
    public String getEmptype() {
        return emptype;
    }
    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "NewEmployeeDTO [name=" + name + ", email=" + email + ", gender=" + gender + ", designation="
				+ designation + ", department=" + department + ", gradeLevel=" + gradeLevel + ", doj=" + doj
				+ ", emptype=" + emptype + ", username=" + username + ", password=" + password + ", yearOfExperience="
				+ yearOfExperience + "]";
	}
   
    
}
