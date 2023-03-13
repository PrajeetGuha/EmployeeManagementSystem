package org.antwalk.ems.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.antwalk.ems.model.Employee;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeReport {
    
    public static void generateReport(XSSFWorkbook workbook, Employee employee){
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

        List<String> valueList = Arrays.asList(
            employee.getEmpId().toString(),
            employee.getEmpName(),
            employee.getGender(),
            employee.getGradeLevel(),
            employee.getDoj().toString(),
            employee.getDesignation(),
            employee.getEmptype(),
            employee.getEmpstatus(),
            employee.getProbPeriod().toString(),
            employee.getProbCompDate().toString(),
            employee.getTrainPeriod().toString(),
            employee.getContractEndDate().toString(),
            employee.getServPeriod().toString()


        );

        for(int i = 2+rowIterator; i < fieldnames.size(); i++){
            XSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(fieldnames.get(rowIterator));
        }
    }
}
