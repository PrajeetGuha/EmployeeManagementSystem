package org.antwalk.ems.view;

import org.antwalk.ems.model.Employee;

public class TeamListView2 {
	Long teamId;
	String teamName;
	String department;
	String tm;
	
	public TeamListView2(Long teamId, String teamName, String department, String tm) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.department = department;
		this.tm = tm;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	
}
