package org.antwalk.ems.dto;

public class TeamSelectionViewDTO {
    private Long teamId;
    private String teamName;
    public TeamSelectionViewDTO() {
    }
    public TeamSelectionViewDTO(Long teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
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
    
}
