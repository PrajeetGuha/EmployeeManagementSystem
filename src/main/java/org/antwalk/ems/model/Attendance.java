package org.antwalk.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empAttId;

    @Column(columnDefinition = "ENUM('Y','N')")
    private String isApproved;

    @Column(columnDefinition = "ENUM('CL','PL','SL','OL')")
    private String leaveType;

    @Column
    private String leaveReason;

    @ManyToOne
    @JsonIgnoreProperties("attendances")
    private Employee employee;

    @OneToOne
    @JsonIgnoreProperties("attendance")
    private Admin admin;

    public Attendance() {
    }

    public Attendance(Long empAttId, String isApproved, String leaveType, String leaveReason, Employee employee,
            Admin admin) {
        this.empAttId = empAttId;
        this.isApproved = isApproved;
        this.leaveType = leaveType;
        this.leaveReason = leaveReason;
        this.employee = employee;
        this.admin = admin;
    }

    public Long getEmpAttId() {
        return empAttId;
    }

    public void setEmpAttId(Long empAttId) {
        this.empAttId = empAttId;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
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
