package org.antwalk.ems.dto;

import java.sql.Date;

public class NewProjectDTO {
	private String projectName;
	private Date startDate;
	private Date endDate;
	
	
	public NewProjectDTO() {
	}


	public NewProjectDTO(String projectName, Date startDate, Date endDate) {
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
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
	

}
