package org.antwalk.ems.dto;

import java.sql.Date;

public class ResignationDTO {
    
    private String resignationReason;

    private Date resignationDate;

    private String approvedBy;

    private Boolean isApproved;

    public ResignationDTO(String resignationReason, Date resignationDate, String approvedBy, Boolean isApproved) {
        this.resignationReason = resignationReason;
        this.resignationDate = resignationDate;
        this.approvedBy = approvedBy;
        this.isApproved = isApproved;
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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    
}
