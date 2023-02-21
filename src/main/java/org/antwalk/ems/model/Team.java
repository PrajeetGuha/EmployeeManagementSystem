package org.antwalk.ems.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private String tm;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    @ManyToOne
    private Project project;

    public Team() {
    }

    public Team(Long teamId, String tm, Department department, List<Employee> employees, Project project) {
        this.teamId = teamId;
        this.tm = tm;
        this.department = department;
        this.employees = employees;
        this.project = project;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTm() {
        return tm;
    }

    public void setGm(String tm) {
        this.tm = tm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
}