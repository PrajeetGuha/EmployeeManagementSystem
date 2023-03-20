
package org.antwalk.ems.dto;

public class EditDepartmentDTO {

	private String departmentName;
	private Long hod;
	private String empList;

	public EditDepartmentDTO() {
	}

	public EditDepartmentDTO(String departmentName, Long hod, String empList) {
		this.departmentName = departmentName;
		this.hod = hod;
		this.empList = empList;
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
	