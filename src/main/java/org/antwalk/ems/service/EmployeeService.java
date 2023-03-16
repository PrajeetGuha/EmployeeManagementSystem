package org.antwalk.ems.service;

import java.util.List;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	public List<FamilyDetails> listAllFamilyDetails(Long id){
		return employeeRepository.getById(id).getEmployeeDetails().getListFamilyDetails();
	}
	
	public EmployeeDetails employeeInfo(Long id){
		return employeeRepository.getById(id).getEmployeeDetails();
	}

	public Employee findEmployee(Long id){
		return employeeRepository.getById(id);
	}

}
