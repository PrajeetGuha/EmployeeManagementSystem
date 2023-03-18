package org.antwalk.ems.model;

import java.sql.Date;

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
public class Resignation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resignation_id;

    @Column
    private String resignationReason;

    @Column
    private Date resignationDate;

    @OneToOne
    private Admin approvedBy;
    @Column
    private Boolean isApproved;
    
    @OneToOne
    private Employee employee;

    public Resignation() {
    }

	public Resignation(Long resignation_id, String resignationReason, Date resignationDate, Admin approvedBy,
			Boolean isApproved, Employee employee) {
		super();
		this.resignation_id = resignation_id;
		this.resignationReason = resignationReason;
		this.resignationDate = resignationDate;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.employee = employee;
	}

	public Long getResignation_id() {
		return resignation_id;
	}

	public void setResignation_id(Long resignation_id) {
		this.resignation_id = resignation_id;
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

	public Admin getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Admin approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}




}
