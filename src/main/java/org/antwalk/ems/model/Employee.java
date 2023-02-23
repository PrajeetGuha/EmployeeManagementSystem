package org.antwalk.ems.model;


import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private String gl;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date doj;

    @Column
    private String designation;

    @Column
    private String empType;

    @Column
    private String empStatus;

    @Column
    private int probPeriod;

    @Column
    private int trainPeriod;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date contractEndDate;

    @Column
    private int servePeriod;

    @Column
    private boolean isGM;

    @Column
    private boolean isExpat;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("employees")
    private Location location;

    @Column
    private String workstationId;

    @Column
    private Double ctc;

    @Column
    private String userId;

    @Column
    private String emailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("employees")
    private Team team;

    public Employee() {
    }

    public Employee(Long empId, String name, String gender, String gl, Date doj, String designation, String empType,
            String empStatus, int probPeriod, int trainPeriod, Date contractEndDate, int servePeriod, boolean isGM,
            boolean isExpat, Date releaseDate, Location location, String workstationId, Double ctc, String userId,
            String emailId, Team team) {
        this.empId = empId;
        this.name = name;
        this.gender = gender;
        this.gl = gl;
        this.doj = doj;
        this.designation = designation;
        this.empType = empType;
        this.empStatus = empStatus;
        this.probPeriod = probPeriod;
        this.trainPeriod = trainPeriod;
        this.contractEndDate = contractEndDate;
        this.servePeriod = servePeriod;
        this.isGM = isGM;
        this.isExpat = isExpat;
        this.releaseDate = releaseDate;
        this.location = location;
        this.workstationId = workstationId;
        this.ctc = ctc;
        this.userId = userId;
        this.emailId = emailId;
        this.team = team;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public int getProbPeriod() {
        return probPeriod;
    }

    public void setProbPeriod(int probPeriod) {
        this.probPeriod = probPeriod;
    }

    public int getTrainPeriod() {
        return trainPeriod;
    }

    public void setTrainPeriod(int trainPeriod) {
        this.trainPeriod = trainPeriod;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public int getServePeriod() {
        return servePeriod;
    }

    public void setServePeriod(int servePeriod) {
        this.servePeriod = servePeriod;
    }

    public boolean isGM() {
        return isGM;
    }

    public void setGM(boolean isGM) {
        this.isGM = isGM;
    }

    public boolean isExpat() {
        return isExpat;
    }

    public void setExpat(boolean isExpat) {
        this.isExpat = isExpat;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(String workstationId) {
        this.workstationId = workstationId;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((empId == null) ? 0 : empId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((gl == null) ? 0 : gl.hashCode());
        result = prime * result + ((doj == null) ? 0 : doj.hashCode());
        result = prime * result + ((designation == null) ? 0 : designation.hashCode());
        result = prime * result + ((empType == null) ? 0 : empType.hashCode());
        result = prime * result + ((empStatus == null) ? 0 : empStatus.hashCode());
        result = prime * result + probPeriod;
        result = prime * result + trainPeriod;
        result = prime * result + ((contractEndDate == null) ? 0 : contractEndDate.hashCode());
        result = prime * result + servePeriod;
        result = prime * result + (isGM ? 1231 : 1237);
        result = prime * result + (isExpat ? 1231 : 1237);
        result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((workstationId == null) ? 0 : workstationId.hashCode());
        result = prime * result + ((ctc == null) ? 0 : ctc.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
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
        Employee other = (Employee) obj;
        if (empId == null) {
            if (other.empId != null)
                return false;
        } else if (!empId.equals(other.empId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (gl == null) {
            if (other.gl != null)
                return false;
        } else if (!gl.equals(other.gl))
            return false;
        if (doj == null) {
            if (other.doj != null)
                return false;
        } else if (!doj.equals(other.doj))
            return false;
        if (designation == null) {
            if (other.designation != null)
                return false;
        } else if (!designation.equals(other.designation))
            return false;
        if (empType == null) {
            if (other.empType != null)
                return false;
        } else if (!empType.equals(other.empType))
            return false;
        if (empStatus == null) {
            if (other.empStatus != null)
                return false;
        } else if (!empStatus.equals(other.empStatus))
            return false;
        if (probPeriod != other.probPeriod)
            return false;
        if (trainPeriod != other.trainPeriod)
            return false;
        if (contractEndDate == null) {
            if (other.contractEndDate != null)
                return false;
        } else if (!contractEndDate.equals(other.contractEndDate))
            return false;
        if (servePeriod != other.servePeriod)
            return false;
        if (isGM != other.isGM)
            return false;
        if (isExpat != other.isExpat)
            return false;
        if (releaseDate == null) {
            if (other.releaseDate != null)
                return false;
        } else if (!releaseDate.equals(other.releaseDate))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (workstationId == null) {
            if (other.workstationId != null)
                return false;
        } else if (!workstationId.equals(other.workstationId))
            return false;
        if (ctc == null) {
            if (other.ctc != null)
                return false;
        } else if (!ctc.equals(other.ctc))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (emailId == null) {
            if (other.emailId != null)
                return false;
        } else if (!emailId.equals(other.emailId))
            return false;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        return true;
    }

    
}
