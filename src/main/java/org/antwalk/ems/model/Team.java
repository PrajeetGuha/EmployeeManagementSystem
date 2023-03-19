package org.antwalk.ems.model;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private String teamName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Employee tm;

    @ManyToMany
    @JoinTable(
        name = "team_project",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "proj_id"))
    private Set<Project> projects;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;
    
    public Team() {
    	
    }

    public Team(Long teamId, String teamName, Employee tm, Set<Project> projects, List<Employee> employees) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.tm = tm;
        this.projects = projects;
        this.employees = employees;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Employee getTm() {
        return tm;
    }

    public void setTm(Employee tm) {
        this.tm = tm;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    

    
}
