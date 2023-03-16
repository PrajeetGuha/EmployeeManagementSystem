package org.antwalk.ems.dto;

public class ChangePasswordDTO {
    private Long empId;
    private String newPassword;

    // getters and setters
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
