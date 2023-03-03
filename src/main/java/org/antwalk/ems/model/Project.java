package org.antwalk.ems.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projId;

    @Column(length = 50)
    private String projectName;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column(length = 50)
    private String pm;

    @ManyToMany
    private Set<Project> projects;

    public Project() {
    }

    public Project(Long projId, String projectName, Date startDate, Date endDate, String pm, Set<Project> projects) {
        this.projId = projId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pm = pm;
        this.projects = projects;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    
}
