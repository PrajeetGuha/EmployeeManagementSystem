package org.antwalk.ems.dto;

import java.sql.Date;

public class ResignationListDTO {
    private Long resignationId;
    private String resignationReason;
    private Date resignationDate;
    private Boolean isApproved;
    private String empName;
    public ResignationListDTO() {
    }
    public ResignationListDTO(Long resignationId, String resignationReason, Date resignationDate, Boolean isApproved,
            String empName) {
        this.resignationId = resignationId;
        this.resignationReason = resignationReason;
        this.resignationDate = resignationDate;
        this.isApproved = isApproved;
        this.empName = empName;
    }
    public Long getResignationId() {
        return resignationId;
    }
    public void setResignationId(Long resignationId) {
        this.resignationId = resignationId;
    }
    public String getResignationReason() {
        return resignationReason;
    }
    public void setResignationReason(String resignationReason) {
        this.resignationReason = resignationReason;
    }
    public Date getResignationDate() {
        return resignationDate;
    }
    public void setResignationDate(Date resignationDate) {
        this.resignationDate = resignationDate;
    }
    public Boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    
    
}
