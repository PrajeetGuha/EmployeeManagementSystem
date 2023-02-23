package org.antwalk.ems.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Location implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locId;

    @Column
    private String branch;

    @Column
    private String office;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("location")
    private List<Employee> employees;

    public Location() {
    }

    public Location(Long locId, String branch, String office, List<Employee> employees) {
        this.locId = locId;
        this.branch = branch;
        this.office = office;
        this.employees = employees;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((locId == null) ? 0 : locId.hashCode());
        result = prime * result + ((branch == null) ? 0 : branch.hashCode());
        result = prime * result + ((office == null) ? 0 : office.hashCode());
        result = prime * result + ((employees == null) ? 0 : employees.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (locId == null) {
            if (other.locId != null)
                return false;
        } else if (!locId.equals(other.locId))
            return false;
        if (branch == null) {
            if (other.branch != null)
                return false;
        } else if (!branch.equals(other.branch))
            return false;
        if (office == null) {
            if (other.office != null)
                return false;
        } else if (!office.equals(other.office))
            return false;
        if (employees == null) {
            if (other.employees != null)
                return false;
        } else if (!employees.equals(other.employees))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location [locId=" + locId + ", branch=" + branch + ", office=" + office + ", employees=" + employees
                + "]";
    }

    
    
}
