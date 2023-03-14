package org.antwalk.ems.service;

import java.util.List;

import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<FamilyDetails> listAllFamilyDetails(Long id){
		return employeeRepository.getById(id).getEmployeeDetails().getListFamilyDetails();
	}
	
	public EmployeeDetails EmployeeInfo(Long id){
		return employeeRepository.getById(id).getEmployeeDetails();
	}
}
