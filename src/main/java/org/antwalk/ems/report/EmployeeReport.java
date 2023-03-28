package org.antwalk.ems.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.ProfDetails;
import org.antwalk.ems.model.QualificationDetails;
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

List<String> fieldnames = Arrays.asList("Id","Name","Gender", "Grade/Level","DOJ", "Designation","Employment Type","Employment Status",
		"Work Email", "Branch and Office", "Workstation Id","Current CTC",  "Department",  "Team","Marital Status", "Permanent Address","Personal Email Id", "Primary Contact Details", "Emergency Contact Details");
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
// employee.getProbPeriod(),
// employee.getProbCompDate(),
// employee.getTrainPeriod(),
// employee.getContractEndDate(),
// employee.getServPeriod(),
 employee.getWorkEmail(),
 employee.getBranch() + " " + employee.getOffice(),
 employee.getWorkstationId(),
 employee.getCtc(),
department,
team,
employee.getEmployeeDetails().getMaritalStatus(),
 employee.getEmployeeDetails().getPermaAddress(),
 employee.getEmployeeDetails().getEmailId(),
 employee.getEmployeeDetails().getPrimaryContactno(),
 employee.getEmployeeDetails().getEmergencyContactno());


//System.out.println("\n\n\n"+valueList.size()+"\n\n\n"+fieldnames.size());

List<String> nonullValueList = new ArrayList<>();
for(int i = 0; i < valueList.size(); i++){

nonullValueList.add( valueList.get(i) == null ? "" : valueList.get(i).toString());

}

for(int i = 2+rowIterator; i < fieldnames.size()+2; i++){
XSSFRow row = s1.createRow(i);
 XSSFCell descriptorCell = row.createCell(0);
 descriptorCell.setCellStyle(style.descriptorFieldStyle(workbook));
descriptorCell.setCellValue(fieldnames.get(rowIterator));
row.createCell(1).setCellValue(nonullValueList.get(rowIterator));
 rowIterator += 1;
}





XSSFSheet s2 = workbook.createSheet(employee.getEmpName() + "-Qualification Details");

XSSFRow s2r1 = s2.createRow(0);
s2r1.createCell(0).setCellValue("Qualification Details");

int rowIterator2 = 0;

List<String> fieldnames2 = new ArrayList<>();
List<QualificationDetails> qualificationDetails=employee.getEmployeeDetails().getListQualificationDetails();
fieldnames2.add("id");
fieldnames2.add("name");
List<Object> valueList2 =new ArrayList<>();
valueList2.add(employee.getEmpId());
valueList2.add(employee.getEmpName());
//fieldnames2.add("hi");
for(int i=0;i<qualificationDetails.size();i++) {
//	System.out.println("started\n\n");
	int val=i+1;
	fieldnames2.add("Qualification "+val+" Name");
	fieldnames2.add("Qualification "+val+" Start date");
	fieldnames2.add("Qualification "+val+" End date");
	fieldnames2.add("Qualification "+val+" Percentage");
//	System.out.println("started again\n\n");
	valueList2.add(qualificationDetails.get(i).getQual());
//	System.out.println(qualificationDetails.get(i).getQual());
	valueList2.add(qualificationDetails.get(i).getStartDate());
//	System.out.println(qualificationDetails.get(i).getStartDate());
	valueList2.add(qualificationDetails.get(i).getEndDate());
//	System.out.println(qualificationDetails.get(i).getEndDate());
	valueList2.add(qualificationDetails.get(i).getPercent());
//	System.out.println(qualificationDetails.get(i).getPercent());
}

//System.out.println("\n hi \n\n"+valueList2.size()+"\n\n\n"+fieldnames2.size());

List<String> nonullValueList2 = new ArrayList<>();
for(int i = 0; i < valueList2.size(); i++){

nonullValueList2.add( valueList2.get(i) == null ? "" : valueList2.get(i).toString());

}

for(int i = 2+rowIterator2; i < fieldnames2.size()+2; i++){
XSSFRow row = s2.createRow(i);
XSSFCell descriptorCell = row.createCell(0);
descriptorCell.setCellStyle(style.descriptorFieldStyle(workbook));
descriptorCell.setCellValue(fieldnames2.get(rowIterator2));
row.createCell(1).setCellValue(nonullValueList2.get(rowIterator2));
rowIterator2 += 1;
}



//int numCol2 = 2;
//for(int i = 0; i < numCol2; i++){
//s2.autoSizeColumn(i);}




XSSFSheet s3 = workbook.createSheet(employee.getEmpName() + "-Professional Details");

XSSFRow s3r1 = s3.createRow(0);
s3r1.createCell(0).setCellValue("Professional Details");

int rowIterator3 = 0;

List<String> fieldnames3 = new ArrayList<>();
List<ProfDetails> profDetails=employee.getEmployeeDetails().getListProfDetails();
fieldnames3.add("id");
fieldnames3.add("name");
List<Object> valueList3 =new ArrayList<>();
valueList3.add(employee.getEmpId());
valueList3.add(employee.getEmpName());
//fieldnames2.add("hi");
for(int i=0;i<profDetails.size();i++) {
//	System.out.println("started\n\n");
	int val=i+1;
	fieldnames3.add("Organisation "+val+" Name");
	fieldnames3.add("Organisation "+val+" Start date");
	fieldnames3.add("Organisation "+val+" End date");
	fieldnames3.add("Organisation "+val+" Designation");
//	System.out.println("started again\n\n");
	valueList3.add(profDetails.get(i).getNameOfPrevOrg());
//	System.out.println(qualificationDetails.get(i).getQual());
	valueList3.add(profDetails.get(i).getFromDate());
//	System.out.println(qualificationDetails.get(i).getStartDate());
	valueList3.add(profDetails.get(i).getToDate());
//	System.out.println(qualificationDetails.get(i).getEndDate());
	valueList3.add(profDetails.get(i).getDesignation());
//	System.out.println(qualificationDetails.get(i).getPercent());
}


List<String> nonullValueList3 = new ArrayList<>();
for(int i = 0; i < valueList3.size(); i++){

nonullValueList3.add( valueList3.get(i) == null ? "" : valueList3.get(i).toString());

}
System.out.println("\n\n started process");

for(int i = 2+rowIterator3; i < fieldnames3.size()+2; i++){
XSSFRow row = s3.createRow(i);
XSSFCell descriptorCell = row.createCell(0);
descriptorCell.setCellStyle(style.descriptorFieldStyle(workbook));
descriptorCell.setCellValue(fieldnames3.get(rowIterator3));
row.createCell(1).setCellValue(nonullValueList3.get(rowIterator3));
rowIterator3 += 1;
}



int numCol = 2;
for(int i = 0; i < numCol; i++){
s1.autoSizeColumn(i);
s2.autoSizeColumn(i);
s3.autoSizeColumn(i);
}
return workbook;};
}
