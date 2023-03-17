package org.antwalk.ems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
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
        leaveApplication.setApplicationDate(new Date());
        leaveApplicationRepository.save(leaveApplication);
    }

}
