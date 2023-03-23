package org.antwalk.ems.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.report.EmployeeReport;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.ProfDetailsRepository;
import org.antwalk.ems.repository.QualificationDetailsRepository;
import org.antwalk.ems.repository.TeamRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private DepartmentRepository departmentRepository;

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

    @Autowired
    private EmployeeReport employeeReport;
   
    public void generateEmployeeReport(HttpServletResponse response, Long id) throws IOException, EmployeeNotFoundException{
        XSSFWorkbook workbook = new XSSFWorkbook();

        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new EmployeeNotFoundException("The employee is not found")
        );
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=employeeReport.xlsx");
        employeeReport.generateReport(workbook, employee);
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
