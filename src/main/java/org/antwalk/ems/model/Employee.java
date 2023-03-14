package org.antwalk.ems.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column
    private String empName;

    @Column
    private String gender;

    @Column(length = 2)
    private String gradeLevel;

   

    @Column
    private Date doj;

    @Column(length = 50)
    private String designation = "unassigned";

    @Column(length = 255)
    private String emptype;

    @Column
    private String empstatus = "active";

    @Column
    private Integer probPeriod;

    @Column
    private Date probCompDate;

    @Column
    private Integer trainPeriod;

    @Column
    private Date contractEndDate;

    @Column
    private Integer servPeriod;

    @Column(length = 50, unique = true)
    @Email
    private String workEmail;

    @Column(length = 50)
    private String branch;

    @Column(length = 50)
    private String office;

    @Column(length = 5)
    private String workstationId;

    @Column
    private Integer clLeft;

    @Column
    private Integer plLeft;

    @Column
    private Integer slLeft;

    @Column
    private Integer moreLeave;

    @Column
    private Integer totalLeave;

    @Column
    private Double ctc;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Team team;

    @OneToOne
    private EmployeeDetails employeeDetails;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<Attendance> attendances;

    public Employee() {
    }
    



    public Employee(Long empId, String empName, String gender, String gradeLevel, Date doj, String designation,
            String emptype, String empstatus, Integer probPeriod, Date probCompDate, Integer trainPeriod, Date contractEndDate,
            Integer servPeriod, @Email String workEmail, String branch, String office, String workstationId,
            Integer clLeft, Integer plLeft, Integer slLeft, Integer moreLeave, Integer totalLeave, Double ctc, Department department,
            Team team, EmployeeDetails employeeDetails, List<Payroll> payrolls, List<Attendance> attendances) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.doj = doj;
        this.designation = designation;
        this.emptype = emptype;
        this.empstatus = empstatus;
        this.probPeriod = probPeriod;
        this.probCompDate = probCompDate;
        this.trainPeriod = trainPeriod;
        this.contractEndDate = contractEndDate;
        this.servPeriod = servPeriod;
        this.workEmail = workEmail;
        this.branch = branch;
        this.office = office;
        this.workstationId = workstationId;
        this.clLeft = clLeft;
        this.plLeft = plLeft;
        this.slLeft = slLeft;
        this.moreLeave = moreLeave;
        this.totalLeave = totalLeave;
        this.ctc = ctc;
        this.department = department;
        this.team = team;
        this.employeeDetails = employeeDetails;
        this.payrolls = payrolls;
        this.attendances = attendances;
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

    public String getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
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

    public void setProbCompDate(Date probCompDate) {
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

    public Integer getClLeft() {
        return clLeft;
    }

    public void setClLeft(Integer clLeft) {
        this.clLeft = clLeft;
    }

    public Integer getPlLeft() {
        return plLeft;
    }

    public void setPlLeft(Integer plLeft) {
        this.plLeft = plLeft;
    }

    public Integer getSlLeft() {
        return slLeft;
    }

    public void setSlLeft(Integer slLeft) {
        this.slLeft = slLeft;
    }

    public Integer getMoreLeave() {
        return moreLeave;
    }

    public void setMoreLeave(Integer moreLeave) {
        this.moreLeave = moreLeave;
    }

    public Integer getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(Integer totalLeave) {
        this.totalLeave = totalLeave;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    
}
