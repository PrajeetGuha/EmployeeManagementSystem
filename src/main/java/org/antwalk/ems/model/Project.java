package org.antwalk.ems.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Project implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projId;

    @Column
    private String projectName;

    @Column
    private String pm;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    private List<Team> teams;

    public Project() {
    }

    public Project(Long projId, String projectName, String pm, List<Team> teams) {
        this.projId = projId;
        this.projectName = projectName;
        this.pm = pm;
        this.teams = teams;
    }

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((projId == null) ? 0 : projId.hashCode());
        result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((pm == null) ? 0 : pm.hashCode());
        result = prime * result + ((teams == null) ? 0 : teams.hashCode());
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
        Project other = (Project) obj;
        if (projId == null) {
            if (other.projId != null)
                return false;
        } else if (!projId.equals(other.projId))
            return false;
        if (projectName == null) {
            if (other.projectName != null)
                return false;
        } else if (!projectName.equals(other.projectName))
            return false;
        if (pm == null) {
            if (other.pm != null)
                return false;
        } else if (!pm.equals(other.pm))
            return false;
        if (teams == null) {
            if (other.teams != null)
                return false;
        } else if (!teams.equals(other.teams))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [projId=" + projId + ", projectName=" + projectName + ", pm=" + pm + ", teams=" + teams + "]";
    }


}
