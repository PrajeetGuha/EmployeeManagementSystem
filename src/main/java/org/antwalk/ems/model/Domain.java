package org.antwalk.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Domain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domId;

    @Column
    private String userId;

    @Column
    private String emailId;

    @OneToOne
    private Employee employee;

    public Domain() {
    }

    public Domain(Long domId, String userId, String emailId, Employee employee) {
        this.domId = domId;
        this.userId = userId;
        this.emailId = emailId;
        this.employee = employee;
    }

    public Long getDomId() {
        return domId;
    }

    public void setDomId(Long domId) {
        this.domId = domId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
