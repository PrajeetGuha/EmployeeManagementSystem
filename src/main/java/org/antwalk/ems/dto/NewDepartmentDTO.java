package org.antwalk.ems.dto;

public class NewDepartmentDTO {
    
    private String departmentName;
    private Long hod;
    public NewDepartmentDTO() {
    }
    public NewDepartmentDTO(String departmentName, Long hod) {
        this.departmentName = departmentName;
        this.hod = hod;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getHod() {
        return hod;
    }
    public void setHod(Long hod) {
        this.hod = hod;
    }
    @Override
    public String toString() {
        return "NewDepartmentDTO [departmentName=" + departmentName + ", hod=" + hod + "]";
    }

    
}
