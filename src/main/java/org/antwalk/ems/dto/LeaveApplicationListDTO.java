package org.antwalk.ems.dto;

import java.sql.Date;

public class LeaveApplicationListDTO {
    private Long empLeaveId;
    private String leaveType;
    private String empName;
    private String leaveReason;
    private Date applicationDate;
    private Boolean isApproved;
    private Date leaveAppliedFor; 
    public LeaveApplicationListDTO() {
    }
    public LeaveApplicationListDTO(Long empLeaveId, String leaveType, String empName, String leaveReason,
            Date applicationDate, Boolean isApproved, Date leaveAppliedFor) {
        this.empLeaveId = empLeaveId;
        this.leaveType = leaveType;
        this.empName = empName;
        this.leaveReason = leaveReason;
        this.applicationDate = applicationDate;
        this.isApproved = isApproved;
        this.leaveAppliedFor = leaveAppliedFor;
    }
    public Long getEmpLeaveId() {
        return empLeaveId;
    }
    public void setEmpLeaveId(Long empLeaveId) {
        this.empLeaveId = empLeaveId;
    }
    public String getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getLeaveReason() {
        return leaveReason;
    }
    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
    public Date getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
    public Boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }
    public Date getLeaveAppliedFor() {
        return leaveAppliedFor;
    }
    public void setLeaveAppliedFor(Date leaveAppliedFor) {
        this.leaveAppliedFor = leaveAppliedFor;
    }
    
}
