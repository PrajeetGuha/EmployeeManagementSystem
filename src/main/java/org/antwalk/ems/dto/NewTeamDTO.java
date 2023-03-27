package org.antwalk.ems.dto;

public class NewTeamDTO {

	private String teamName;
	private Long tm;
	private String department;
	
	public NewTeamDTO() {
		super();
	}
	
	public NewTeamDTO(String teamName, Long tm, String department) {
		super();
		this.teamName = teamName;
		this.tm = tm;
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getTm() {
		return tm;
	}
	public void setTm(Long tm) {
		this.tm = tm;
	}
	@Override
	public String toString() {
		return "NewTeamDTO [teamName=" + teamName + ", tm=" + tm + "]";
	}
	
	
}
