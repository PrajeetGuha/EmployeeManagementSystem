package org.antwalk.ems.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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


    // @Column
    // private boolean isActive=true;
   


    @Column
    private Date doj;

    @Column(length = 255)
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

    @Column(length = 255, unique = true)
    @Email
    private String workEmail;

    @Column(length = 255)
    private String branch;

    @Column(length = 255)
    private String office;

    @Column(length = 255)
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
    private Double ctc=0.0;
    @Column
    private Integer yearOfExperience;

    @OneToOne
    private Resignation resignation;
    




@Column
    private String department;


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
    private List<LeaveApplication> leaves;
    
    public void setDepartment(String department) {
		this.department = department;
	}


   
    // public boolean isActive() {
	// 	return isActive;
	// }

	// public void setActive(boolean isActive) {
	// 	this.isActive = isActive;
	// }

	public String getDepartment() {
		return department;
	}


	public Employee(Long empId, String empName, String gender, String gradeLevel, Date doj,
			String designation, String emptype, String empstatus, Integer probPeriod, Date probCompDate,
			Integer trainPeriod, Date contractEndDate, Integer servPeriod, @Email String workEmail, String branch,
			String office, String workstationId, Integer clLeft, Integer plLeft, Integer slLeft, Integer moreLeave,
			Integer totalLeave, Double ctc, Integer yearOfExperience, Resignation resignation, String department,
			Team team, EmployeeDetails employeeDetails, List<Payroll> payrolls, List<LeaveApplication> leaves) {


		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.gradeLevel = gradeLevel;
		// this.isActive = isActive;
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
		this.yearOfExperience = yearOfExperience;
		this.resignation = resignation;
		this.yearOfExperience = yearOfExperience;
		this.department = department;
		this.team = team;
		this.employeeDetails = employeeDetails;
		this.payrolls = payrolls;
		this.leaves = leaves;
	}


	public Integer getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(Integer yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public Employee() {
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

	public Resignation getResignation() {
		return resignation;
	}

	public void setResignation(Resignation resignation) {
		this.resignation = resignation;
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

	public List<LeaveApplication> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeaveApplication> leaves) {
		this.leaves = leaves;
	}



	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", gender=" + gender + ", gradeLevel=" + gradeLevel
				+ ", doj=" + doj + ", designation=" + designation + ", emptype=" + emptype + ", empstatus=" + empstatus
				+ ", probPeriod=" + probPeriod + ", probCompDate=" + probCompDate + ", trainPeriod=" + trainPeriod
				+ ", contractEndDate=" + contractEndDate + ", servPeriod=" + servPeriod + ", workEmail=" + workEmail
				+ ", branch=" + branch + ", office=" + office + ", workstationId=" + workstationId + ", clLeft="
				+ clLeft + ", plLeft=" + plLeft + ", slLeft=" + slLeft + ", moreLeave=" + moreLeave + ", totalLeave="
				+ totalLeave + ", ctc=" + ctc + ", yearOfExperience=" + yearOfExperience + ", resignation="
				+ resignation + ", department=" + department + ", team=" + team + ", employeeDetails=" + employeeDetails
				+ ", payrolls=" + payrolls + ", leaves=" + leaves + "]";
	}  
}
