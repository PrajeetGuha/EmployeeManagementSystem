package org.antwalk.ems.dto;

public class NewTeamDTO {

	private String teamName;
	private String department;
	
	public NewTeamDTO() {
	}

	public NewTeamDTO(String teamName, String department) {
		this.teamName = teamName;
		this.department = department;
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
	
	
	
}
