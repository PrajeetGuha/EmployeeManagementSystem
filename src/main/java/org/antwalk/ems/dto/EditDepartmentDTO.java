
package org.antwalk.ems.dto;

import java.sql.Date;

public class EditDepartmentDTO {

	private String departmentName;
	private Long hod;
	private String empList;
	private String teamList;

	public EditDepartmentDTO() {
	}


	public String getTeamList() {
		return teamList;
	}


	public void setTeamList(String teamList) {
		this.teamList = teamList;
	}


	public EditDepartmentDTO(String departmentName, Long hod, String empList, String teamList) {
		super();
		this.departmentName = departmentName;
		this.hod = hod;
		this.empList = empList;
		this.teamList = teamList;
	}


	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getHod() {
		return hod;
	}

	public void setHod(Long hod) {
		this.hod = hod;
	}

	public String getEmpList() {
		return empList;
	}

	@Override
	public String toString() {
		return "EditDepartmentDTO [departmentName=" + departmentName + ", hod=" + hod + ", empList=" + empList + "]";
	}

	public void setEmpList(String empList) {
		this.empList = empList;
	}

}
	