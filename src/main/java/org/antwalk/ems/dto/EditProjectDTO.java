package org.antwalk.ems.dto;

import java.sql.Date;
import java.util.List;

public class EditProjectDTO {
	private String projectName;
	private Long pm;
	private Date startDate;
	private Date endDate;
	private String teamList;

	public EditProjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EditProjectDTO(String projectName, Long pm, Date startDate, Date endDate, String teamList) {
		super();
		this.projectName = projectName;
		this.pm = pm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.teamList = teamList;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getPm() {
		return pm;
	}
	public void setPm(Long pm) {
		this.pm = pm;
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
	public String getTeamList() {
		return teamList;
	}
	public void setTeamList(String teamList) {
		this.teamList = teamList;
	}
	
	

}
