package org.antwalk.ems.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antwalk.ems.model.Employee;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReport {

	@Autowired
	private Style style;

	public XSSFWorkbook generateReport(XSSFWorkbook workbook, Employee employee){

    	XSSFSheet s1 = workbook.createSheet(employee.getEmpName() + "-Employee Details");

    	XSSFRow s1r1 = s1.createRow(0);
    	s1r1.createCell(0).setCellValue("Employee Details");

    	int rowIterator = 0;

List<String> fieldnames = Arrays.asList("Id","Name","Gender", "Grade/Level","DOJ", "Designation","Employment Type","Employment Status","Probation Period", "Probation Completion Date", "Train Period","Contract End Date", "Service Period", "Work Email", "Branch and Office", "Workstation Id","Current CTC",  "Department",  "Team","Marital Status", "Permanent Address", "Primary Contact Details","Personal Email Id");
String department = ( employee.getDepartment() == null ? "" : employee.getDepartment());
 String team = ( employee.getTeam() == null ? "" : employee.getTeam().getTeamName() );

List<Object> valueList = Arrays.asList(
employee.getEmpId(),
 employee.getEmpName(),
 employee.getGender(),
 employee.getGradeLevel(),
employee.getDoj(),
 employee.getDesignation(),
 employee.getEmptype(),
employee.getEmpstatus(),
 employee.getProbPeriod(),
 employee.getProbCompDate(),
 employee.getTrainPeriod(),
 employee.getContractEndDate(),
 employee.getServPeriod(),
 employee.getWorkEmail(),
 employee.getBranch() + " " + employee.getOffice(),
 employee.getWorkstationId(),
 employee.getCtc(),
department,
team,
employee.getEmployeeDetails().getMaritalStatus(),
 employee.getEmployeeDetails().getPermaAddress(),
 employee.getEmployeeDetails().getEmailId());

List<String> nonullValueList = new ArrayList<>();
for(int i = 0; i < valueList.size(); i++){

nonullValueList.add( valueList.get(i) == null ? "" : valueList.get(i).toString());

}

for(int i = 2+rowIterator; i < fieldnames.size(); i++){
XSSFRow row = s1.createRow(i);
 XSSFCell descriptorCell = row.createCell(0);
 descriptorCell.setCellStyle(style.descriptorFieldStyle(workbook));
descriptorCell.setCellValue(fieldnames.get(rowIterator));
row.createCell(1).setCellValue(nonullValueList.get(rowIterator));
 rowIterator += 1;
}


int numCol = 2;
for(int i = 0; i < numCol; i++){
s1.autoSizeColumn(i);}
return workbook;};
}
