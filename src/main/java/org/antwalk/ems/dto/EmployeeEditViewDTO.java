package org.antwalk.ems.dto;

import java.sql.Date;

import org.antwalk.ems.view.EmployeeEditView;

public class EmployeeEditViewDTO {
    private Long empId;
    private String empName;
    private String gender;
    private String gradeLevel;
    private Date doj;
    private String designation;
    private String emptype;
    private Integer probPeriod;
    private Date probCompDate;
    private Integer trainPeriod;
    private Date contractEndDate;
    private Integer servPeriod;
    private String workEmail;
    private String branch;
    private String office;
    private String workstationId;
    private Double ctc;
    private Integer yearOfExperience;
    private String department;
    private String teamName;
    public EmployeeEditViewDTO() {
    }
    public EmployeeEditViewDTO(Long empId, String empName, String gender, String gradeLevel, Date doj,
            String designation, String emptype, Integer probPeriod, Date probCompDate, Integer trainPeriod,
            Date contractEndDate, Integer servPeriod, String workEmail, String branch, String office,
            String workstationId, Double ctc, Integer yearOfExperience, String department, String teamName) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.doj = doj;
        this.designation = designation;
        this.emptype = emptype;
        this.probPeriod = probPeriod;
        this.probCompDate = probCompDate;
        this.trainPeriod = trainPeriod;
        this.contractEndDate = contractEndDate;
        this.servPeriod = servPeriod;
        this.workEmail = workEmail;
        this.branch = branch;
        this.office = office;
        this.workstationId = workstationId;
        this.ctc = ctc;
        this.yearOfExperience = yearOfExperience;
        this.department = department;
        this.teamName = teamName;
    }
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGradeLevel() {
        return gradeLevel;
    }
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
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
    public String getEmptype() {
        return emptype;
    }
    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }
    public Integer getProbPeriod() {
        return probPeriod;
    }
    public void setProbPeriod(Integer probPeriod) {
        this.probPeriod = probPeriod;
    }
    public Date getProbCompDate() {
        return probCompDate;
    }
    public void setProbCompPeriod(Date probCompDate) {
        this.probCompDate = probCompDate;
    }
    public Integer getTrainPeriod() {
        return trainPeriod;
    }
    public void setTrainPeriod(Integer trainPeriod) {
        this.trainPeriod = trainPeriod;
    }
    public Date getContractEndDate() {
        return contractEndDate;
    }
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }
    public Integer getServPeriod() {
        return servPeriod;
    }
    public void setServPeriod(Integer servPeriod) {
        this.servPeriod = servPeriod;
    }
    public String getWorkEmail() {
        return workEmail;
    }
    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
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
    public Integer getYearOfExperience() {
        return yearOfExperience;
    }
    public void setYearOfExperience(Integer yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    
}
