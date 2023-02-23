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
public class Department implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column
    private String departmentName;

    @Column
    private String hod;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Team> teams;

    public Department() {
    }

    public Department(Long deptId, String departmentName, String hod, List<Team> teams) {
        this.deptId = deptId;
        this.departmentName = departmentName;
        this.hod = hod;
        this.teams = teams;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
        result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
        result = prime * result + ((hod == null) ? 0 : hod.hashCode());
        result = prime * result + ((teams == null) ? 0 : teams.hashCode());
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
        Department other = (Department) obj;
        if (deptId == null) {
            if (other.deptId != null)
                return false;
        } else if (!deptId.equals(other.deptId))
            return false;
        if (departmentName == null) {
            if (other.departmentName != null)
                return false;
        } else if (!departmentName.equals(other.departmentName))
            return false;
        if (hod == null) {
            if (other.hod != null)
                return false;
        } else if (!hod.equals(other.hod))
            return false;
        if (teams == null) {
            if (other.teams != null)
                return false;
        } else if (!teams.equals(other.teams))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", departmentName=" + departmentName + ", hod=" + hod + ", teams="
                + teams + "]";
    }

    
}
