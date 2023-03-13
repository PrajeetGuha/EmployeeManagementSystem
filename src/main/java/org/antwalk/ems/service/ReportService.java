package org.antwalk.ems.service;

import javax.servlet.http.HttpServletResponse;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.repository.EmployeeRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    public void generateEmployeeReport(HttpServletResponse response, Long id){
        HSSFWorkbook workbook = new HSSFWorkbook();

        Employee employee = employeeRepository.getById(id);
        HSSFSheet sheet = workbook.createSheet(employee.getEmpName());
    }
}
