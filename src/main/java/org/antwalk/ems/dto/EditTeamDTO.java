package org.antwalk.ems.dto;

public class EditTeamDTO {
	private String teamName;
	private Long tm;
	private String empList;
	
	
	
	
	public EditTeamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EditTeamDTO(String teamName, Long tm, String empList) {
		super();
		this.teamName = teamName;
		this.tm = tm;
		this.empList = empList;
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
	public String getEmpList() {
		return empList;
	}
	public void setEmpList(String empList) {
		this.empList = empList;
	}
	
	
}
