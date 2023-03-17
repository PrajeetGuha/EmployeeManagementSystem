package org.antwalk.ems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empLeaveId;

    @Column
    private Boolean isApproved = false;

    @Column
    private String leaveType;

    @Column
    private String leaveReason;

    @Column
    private Date leaveAppliedFor;

    @Column
    private Date applicationDate;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private Admin admin;

    
    @Override
    public String toString() {
        return "LeaveApplication [empLeaveId=" + empLeaveId + ", isApproved=" + isApproved + ", leaveType=" + leaveType
                + ", leaveReason=" + leaveReason + ", leaveAppliedFor=" + leaveAppliedFor + ", applicationDate="
                + applicationDate + ", employee=" + employee + ", admin=" + admin + "]";
    }

    public LeaveApplication() {
    }

    public LeaveApplication(Long empLeaveId, Boolean isApproved, String leaveType, String leaveReason,
            Date leaveAppliedFor, Date applicationDate, Employee employee, Admin admin) {
        this.empLeaveId = empLeaveId;
        this.isApproved = isApproved;
        this.leaveType = leaveType;
        this.leaveReason = leaveReason;
        this.leaveAppliedFor = leaveAppliedFor;
        this.applicationDate = applicationDate;
        this.employee = employee;
        this.admin = admin;
    }

    public Long getEmpLeaveId() {
        return empLeaveId;
    }

    public void setEmpLeaveId(Long empLeaveId) {
        this.empLeaveId = empLeaveId;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Date getLeaveAppliedFor() {
        return leaveAppliedFor;
    }

    public void setLeaveAppliedFor(Date leaveAppliedFor) {
        this.leaveAppliedFor = leaveAppliedFor;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    
}