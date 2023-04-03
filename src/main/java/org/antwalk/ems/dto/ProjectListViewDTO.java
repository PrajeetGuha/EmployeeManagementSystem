package org.antwalk.ems.dto;

import java.sql.Date;

public class ProjectListViewDTO {
	Long projId;
	String projectName;
	Date startDate;
	Date endDate;
	String pm;
	public ProjectListViewDTO(Long projId, String projectName, Date startDate, Date endDate, String pm) {
		super();
		this.projId = projId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pm = pm;
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
	
}
