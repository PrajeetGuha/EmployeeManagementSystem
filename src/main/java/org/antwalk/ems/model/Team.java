package org.antwalk.ems.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Team implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private String tm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("team")
    private Department department;

    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties("team")
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("team")
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((tm == null) ? 0 : tm.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((employees == null) ? 0 : employees.hashCode());
        result = prime * result + ((project == null) ? 0 : project.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (teamId == null) {
            if (other.teamId != null)
                return false;
        } else if (!teamId.equals(other.teamId))
            return false;
        if (tm == null) {
            if (other.tm != null)
                return false;
        } else if (!tm.equals(other.tm))
            return false;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (employees == null) {
            if (other.employees != null)
                return false;
        } else if (!employees.equals(other.employees))
            return false;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Team [teamId=" + teamId + ", tm=" + tm + ", department=" + department + ", employees=" + employees
                + ", project=" + project + "]";
    }
    
}