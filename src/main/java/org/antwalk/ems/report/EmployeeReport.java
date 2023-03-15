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
        XSSFSheet sheet = workbook.createSheet(employee.getEmpName() + "-Employee Details");

        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Employee Details");

        int rowIterator = 0;
        List<String> fieldnames = Arrays.asList(
            "Id",
            "Name", 
            "Gender", 
            "Grade/Level", 
            "DOJ", 
            "Designation", 
            "Employment Type", 
            "Employment Status", 
            "Probation Period", 
            "Probation Completion Date", 
            "Train Period", 
            "Contract End Date", 
            "Service Period", 
            "Work Email", 
            "Branch", 
            "Office", 
            "Workstation Id", 
            "Casual Leaves Left", 
            "Personal Leaves Left", 
            "Sick Leaves Left", 
            "Additional Leaves", 
            "Total Leaves Taken", 
            "Current CTC", 
            "Department", 
            "Team", 
            "Marital Status", 
            "Permanent Address", 
            "Primary Contact Details", 
            "Emergency Contact Details", 
            "Personal Email Id", 
            "Present Address", 
            "Nationality", 
            "Blood Group", 
            "Pan Card Number", 
            "Aadhaar Card Number", 
            "Passport Number");

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
            employee.getBranch(),
            employee.getOffice(),
            employee.getWorkstationId(),
            employee.getClLeft(),
            employee.getPlLeft(),
            employee.getSlLeft(),
            employee.getMoreLeave(),
            employee.getTotalLeave(),
            employee.getCtc(),
            employee.getDepartment().getDepartmentName(),
            ( employee.getTeam() == null ? "" : employee.getTeam().getTeamName()),
            employee.getEmployeeDetails().getMaritalStatus(),
            employee.getEmployeeDetails().getPermaAddress(),
            employee.getEmployeeDetails().getEmergencyContactno(),
            employee.getEmployeeDetails().getEmailId(),
            employee.getEmployeeDetails().getPresentAddress(),
            employee.getEmployeeDetails().getNationality(),
            employee.getEmployeeDetails().getBloodGrp(),
            employee.getEmployeeDetails().getPancardnno(),
            employee.getEmployeeDetails().getAdhaarno(),
            employee.getEmployeeDetails().getPassportno()
        );

        List<String> nonullValueList = new ArrayList<>();

        for(int i = 0; i < valueList.size(); i++){

            nonullValueList.add( valueList.get(i) == null ? "" : valueList.get(i).toString());

        }

        for(int i = 2+rowIterator; i < fieldnames.size(); i++){
            XSSFRow row = sheet.createRow(i);
            XSSFCell descriptorCell = row.createCell(0);
            descriptorCell.setCellStyle(style.descriptorFieldStyle(workbook));
            descriptorCell.setCellValue(fieldnames.get(rowIterator));
            row.createCell(1).setCellValue(nonullValueList.get(rowIterator));
            rowIterator += 1;
        }

        int numCol = 2;
        for(int i = 0; i < numCol; i++){
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }
}
