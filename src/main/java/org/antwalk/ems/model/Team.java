package org.antwalk.ems.model;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties("teams")
    @JoinTable(
        name = "team_project",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "proj_id"))
    private Set<Project> projects;

    public Team(){
    	
    }
    
    public Team(Long teamId, String teamName, Employee tm, Set<Project> projects) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.tm = tm;
        this.projects = projects;
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

    
}
