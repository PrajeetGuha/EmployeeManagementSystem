package org.antwalk.ems.dto;

public class ProjectPmDTO {
    public Long pm;

    public ProjectPmDTO(Long pm) {
        this.pm = pm;
    }

    public ProjectPmDTO() {
    }

    public Long getPm() {
        return pm;
    }

    public void setPm(Long pm) {
        this.pm = pm;
    }
}
