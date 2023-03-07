package org.antwalk.ems.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollId;

    @Column
    private Double ctc;

    @Column
    private Double hike;

    @Column
    private Double ctcNew;

    @Column
    @Past
    private Date hikeDt;

    @ManyToOne
    @JsonIgnoreProperties("payrolls")
    private Employee employee;

    public Payroll() {
    }

    public Payroll(Long payrollId, Double ctc, Double hike, Double ctcNew, @Past Date hikeDt, Employee employee) {
        this.payrollId = payrollId;
        this.ctc = ctc;
        this.hike = hike;
        this.ctcNew = ctcNew;
        this.hikeDt = hikeDt;
        this.employee = employee;
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public Double getHike() {
        return hike;
    }

    public void setHike(Double hike) {
        this.hike = hike;
    }

    public Double getCtcNew() {
        return ctcNew;
    }

    public void setCtcNew(Double ctcNew) {
        this.ctcNew = ctcNew;
    }

    public Date getHikeDt() {
        return hikeDt;
    }

    public void setHikeDt(Date hikeDt) {
        this.hikeDt = hikeDt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

}
