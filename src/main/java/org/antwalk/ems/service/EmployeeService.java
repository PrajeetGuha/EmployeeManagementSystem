package org.antwalk.ems.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.ProfDetails;
import org.antwalk.ems.model.QualificationDetails;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
import org.antwalk.ems.repository.ProfDetailsRepository;
import org.antwalk.ems.repository.QualificationDetailsRepository;
import org.antwalk.ems.repository.ResignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	@Value("${fetch.pagesize}")
	private int PAGESIZE;

	@Autowired
	QualificationDetailsRepository qualificationDetailsRepository;
	
//	public List<FamilyDetails> listAllFamilyDetails(Long id){
//		return employeeRepository.getById(id).getEmployeeDetails().getListFamilyDetails();
//	}
	
	public EmployeeDetails employeeInfo(Long id){
		return employeeRepository.getById(id).getEmployeeDetails();
	}

	public Employee findEmployee(Long id) throws EmployeeNotFoundException{
		return employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("The employee is not found"));
	}
	public List<LeaveApplication> findEmployeeLeaves(Long id, int pg) throws EmployeeNotFoundException  {
         employeeRepository.findById(id).orElseThrow(
             () -> new EmployeeNotFoundException("No details found")
         );
         Pageable pageable = PageRequest.of(pg-1, PAGESIZE);
        return employeeRepository.getLeavesById(id,pageable);
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
	
	public void addQualification(Long id, QualificationDetails qualificationDetails) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("The employee is not found")
		);
		EmployeeDetails employeeDetails=employee.getEmployeeDetails();
		qualificationDetailsRepository.save(qualificationDetails);
		employeeDetails.addQualification(qualificationDetails);
		employeeDetailsRepository.save(employeeDetails);
//		employeeDetails.setListQualificationDetails(qualificationDetailsRepository.findQualificationDetails(id));
	}

	public List<QualificationDetails> findQualificationDetails(Long id, int pg)  {
         Employee employee;
		try {
			employee = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
			 );
			Long empDetId=employee.getEmployeeDetails().getEmpDetId();
	        return employeeDetailsRepository.findQualificationDetailsByEmpDetId(empDetId);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Pageable pageable = PageRequest.of(pg-1, 7);
		return null;
    }

	@Autowired
	ProfDetailsRepository profDetailsRepository;

	public void addProfession(Long id, ProfDetails profDetails) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("The employee is not found")
		);
		EmployeeDetails employeeDetails=employee.getEmployeeDetails();
		profDetailsRepository.save(profDetails);
		employeeDetails.addProfessionalDetails(profDetails);
		employeeDetailsRepository.save(employeeDetails);
//		employeeDetails.setListQualificationDetails(qualificationDetailsRepository.findQualificationDetails(id));
	}

	public List<ProfDetails> findProfessionalDetails(Long id, int pg)  {
         Employee employee;
		try {
			employee = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
			 );
			Long empDetId=employee.getEmployeeDetails().getEmpDetId();
	        return employeeDetailsRepository.findProfessionalDetailsByEmpDetId(empDetId);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Pageable pageable = PageRequest.of(pg-1, 7);
		return null;
    }
	
	@Autowired
	FamilyDetailsRepository familyDetailsRepository;
	public void addFamily(Long id, FamilyDetails familyDetails) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("The employee is not found")
		);
		EmployeeDetails employeeDetails=employee.getEmployeeDetails();
		familyDetailsRepository.save(familyDetails);
		employeeDetails.addFamilyDetails(familyDetails);
		employeeDetailsRepository.save(employeeDetails);
//		employeeDetails.setListQualificationDetails(qualificationDetailsRepository.findQualificationDetails(id));
	}

	public List<FamilyDetails> findFamilyDetails(Long id, int pg)  {
         Employee employee;
		try {
			employee = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
			 );
			Long empDetId=employee.getEmployeeDetails().getEmpDetId();
	        return employeeDetailsRepository.findFamilyDetailsByEmpDetId(empDetId);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Pageable pageable = PageRequest.of(pg-1, 7);
		return null;
    }

	public int totalLeaves(Long id) throws EmployeeNotFoundException {
		Employee employee  = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
		);
		return employee.getLeaves() == null ? 0 : employee.getLeaves().size();
	}

	public int totalCountOfPages(Long id) throws EmployeeNotFoundException {
		Employee employee  = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
		);
		Pageable pageable = PageRequest.of(0, PAGESIZE);
		return leaveApplicationRepository.getLeavesById(id, pageable).getTotalPages();
	}

	public List<Integer> countApplied(Long id) {
		List<Integer> applied=new ArrayList<>();
		applied.add(leaveApplicationRepository.getAppliedCL(id));
		applied.add(leaveApplicationRepository.getAppliedPL(id));
		applied.add(leaveApplicationRepository.getAppliedSL(id));
		
		// TODO Auto-generated method stub
		return applied;
	}

	public void deleteFamilyMemberById(Long fid) {
		// TODO Auto-generated method stub
		familyDetailsRepository.deleteById(fid);
		
	}

	public void deleteProfessionById(Long pid) {
		// TODO Auto-generated method stub
		profDetailsRepository.deleteById(pid);
		
	}

	public void deleteQualificationById(Long qid) {
		// TODO Auto-generated method stub
		qualificationDetailsRepository.deleteById(qid);
	}

}
