package org.antwalk.ems.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.antwalk.ems.model.Department;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.repository.DepartmentRepository;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.ProfDetailsRepository;
import org.antwalk.ems.repository.QualificationDetailsRepository;
import org.antwalk.ems.repository.TeamRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private QualificationDetailsRepository qualificationDetailsRepository;

    @Autowired
    private FamilyDetailsRepository familyDetailsRepository;

    @Autowired
    private ProfDetailsRepository profDetailsRepository;
    
    public void generateEmployeeReport(HttpServletResponse response, Long id){
        XSSFWorkbook workbook = new XSSFWorkbook();

        Employee employee = employeeRepository.getById(id);
        XSSFSheet sheet = workbook.createSheet(employee.getEmpName() + "-Employee Details");
        
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Employee Details");

        Employee employeeFromRepository = employeeRepository.getById(id);

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
            employeeFromRepository.getEmpId().toString(),
            employeeFromRepository.getEmpName()
            // employeeFromRepository.getEmp
        );

        for(int i = 2+rowIterator; i < fieldnames.size(); i++){
            XSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(fieldnames.get(rowIterator));
        }
    }
}
