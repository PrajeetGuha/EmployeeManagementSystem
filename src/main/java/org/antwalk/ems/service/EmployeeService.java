package org.antwalk.ems.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
import org.antwalk.ems.repository.ResignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	@Autowired
	private LeaveApplicationRepository leaveApplicationRepository;

	@Autowired
	private ResignationRepository resignationRepository;
	
	public List<FamilyDetails> listAllFamilyDetails(Long id){
		return employeeRepository.getById(id).getEmployeeDetails().getListFamilyDetails();
	}
	
	public EmployeeDetails employeeInfo(Long id){
		return employeeRepository.getById(id).getEmployeeDetails();
	}

	public Employee findEmployee(Long id){
		return employeeRepository.getById(id);
	}
	public List<LeaveApplication> findEmployeeLeaves(Long id, int pg)  {
        // employeeRepository.findById(id).orElseThrow(
        //     () -> new EmployeeNotFoundException("No details found")
        // );
        // Pageable pageable = PageRequest.of(pg-1, 7);
        return employeeRepository.getLeavesById(id);
    }
	public void applyLeave(Long id, LeaveApplication leaveApplication) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("No details found")
        );
		leaveApplication.setEmployee(employee);
        leaveApplication.setApplicationDate(new Date(System.currentTimeMillis()));
        leaveApplicationRepository.save(leaveApplication);
    }

	public Resignation resign(Long id) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("Employee not found")
		);
		return employee.getResignation();
	}

	public void applyForResignation(Long id, Resignation resignation) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("The employee is not found")
		);
		resignation.setEmployee(employee);
		resignation.setResignationDate(new Date(System.currentTimeMillis()));
		employee.setResignation(resignationRepository.save(resignation));
		employeeRepository.save(employee);
	}

}
