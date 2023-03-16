package org.antwalk.ems.dto;

public class ChangePasswordDTO {
    private Long tablePk;
    private String password;

    // getters and setters
    public Long getEmpId() {
        return tablePk;
    }

    public void setEmpId(Long empId) {
        this.tablePk = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
