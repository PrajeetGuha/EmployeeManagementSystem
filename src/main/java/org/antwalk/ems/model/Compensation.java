package org.antwalk.ems.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Compensation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compId;

    @Column
    private Double ctc;

    @OneToMany(mappedBy = "compensation")
    private List<Employee> employees;

    public Compensation() {
    }

    public Compensation(Long compId, Double ctc, List<Employee> employees) {
        this.compId = compId;
        this.ctc = ctc;
        this.employees = employees;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    
}
