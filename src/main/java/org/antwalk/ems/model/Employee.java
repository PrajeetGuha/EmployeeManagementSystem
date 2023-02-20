package org.antwalk.ems.model;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    
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
    private Date contractEnDate;

    @Column
    private int servePeriod;

    @Column
    private int isGM;

    @Column
    private int isExpat;

    @Column
    private Date releasDate;

    @ManyToOne
    @JoinColumn(name = "locId")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "domId")
    private Domain domain;

    @Column
    private String workstationId;

    @ManyToOne
    @JoinColumn(name = "compId")
    private Compensation compensation;

    @ManyToOne
    @JoinColumn(name = "projId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "grpId")
    private Group group;

    public Employee() {
    }

    public Employee(Long empId, String name, String gender, String gl, Date doj, String designation, String empType,
            String empStatus, int probPeriod, int trainPeriod, Date contractEnDate, int servePeriod, int isGM,
            int isExpat, Date releasDate, Location location, Domain domain, String workstationId,
            Compensation compensation, Project project, Group group) {
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
        this.contractEnDate = contractEnDate;
        this.servePeriod = servePeriod;
        this.isGM = isGM;
        this.isExpat = isExpat;
        this.releasDate = releasDate;
        this.location = location;
        this.domain = domain;
        this.workstationId = workstationId;
        this.compensation = compensation;
        this.project = project;
        this.group = group;
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

    public Date getContractEnDate() {
        return contractEnDate;
    }

    public void setContractEnDate(Date contractEnDate) {
        this.contractEnDate = contractEnDate;
    }

    public int getServePeriod() {
        return servePeriod;
    }

    public void setServePeriod(int servePeriod) {
        this.servePeriod = servePeriod;
    }

    public int getIsGM() {
        return isGM;
    }

    public void setIsGM(int isGM) {
        this.isGM = isGM;
    }

    public int getIsExpat() {
        return isExpat;
    }

    public void setIsExpat(int isExpat) {
        this.isExpat = isExpat;
    }

    public Date getReleasDate() {
        return releasDate;
    }

    public void setReleasDate(Date releasDate) {
        this.releasDate = releasDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(String workstationId) {
        this.workstationId = workstationId;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    
    
}
