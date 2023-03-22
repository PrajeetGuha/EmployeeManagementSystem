package org.antwalk.ems.dto;

public class NewTeamDTO {
	
	private String teamName;
	private Long tm;
	public NewTeamDTO() {
		super();
	}
	public NewTeamDTO(String teamName, Long tm) {
		super();
		this.teamName = teamName;
		this.tm = tm;
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
