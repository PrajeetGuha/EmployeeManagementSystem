package org.antwalk.ems.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.antwalk.ems.dto.ResignationDTO;
import org.antwalk.ems.exception.AdminNotFoundException;
import org.antwalk.ems.exception.EmployeeDetailsNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.FamilyDetailsNotFoundException;
import org.antwalk.ems.exception.ProfessionalDetailsNotFoundException;
import org.antwalk.ems.exception.QualificationDetailsNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.ProfDetails;
import org.antwalk.ems.model.QualificationDetails;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
import org.antwalk.ems.repository.ProfDetailsRepository;
import org.antwalk.ems.repository.QualificationDetailsRepository;
import org.antwalk.ems.repository.ResignationRepository;
import org.antwalk.ems.view.LeaveApplicationListView;
import org.antwalk.ems.view.LeaveLeftView;
import org.antwalk.ems.view.ResignationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
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

	@Autowired
	AdminRepository adminRepository;
	
//	public List<FamilyDetails> listAllFamilyDetails(Long id){
//		return employeeRepository.getById(id).getEmployeeDetails().getListFamilyDetails();
//	}
	
	public EmployeeDetails employeeInfo(Long id) throws EmployeeNotFoundException{
		return employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("The employee is not found")
		).getEmployeeDetails();
	}

	public Employee findEmployee(Long id) throws EmployeeNotFoundException{
		return employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("The employee is not found"));
	}
	public List<LeaveApplicationListView> findEmployeeLeaves(Long id, int pg) throws EmployeeNotFoundException  {
         employeeRepository.findById(id).orElseThrow(
             () -> new EmployeeNotFoundException("No employee found")
         );
         Pageable pageable = PageRequest.of(pg-1, PAGESIZE);
        return leaveApplicationRepository.getLeavesById(id,pageable).getContent();
    }
	public void applyLeave(Long id, LeaveApplication leaveApplication) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new EmployeeNotFoundException("The employee is not found")
        );
		leaveApplication.setEmployee(employee);
        leaveApplication.setApplicationDate(new Date(System.currentTimeMillis()));
        leaveApplicationRepository.save(leaveApplication);
    }

	public ResignationDTO resign(Long id) throws EmployeeNotFoundException, AdminNotFoundException {
		System.out.println("Print id:"+id);
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("Employee not found")
		);
		ResignationView resignationView;
		if (employee.getResignation() == null){
			return null;
		}
		else{
			resignationView = resignationRepository.findResignationById(employee.getResignation().getResignation_id());
		}
		String adminName;
		if (resignationView.getApprovedBy() == null){
			adminName = null;
		}
		else{
			Admin admin = adminRepository.findById(resignationView.getApprovedBy()).orElseThrow(
			() -> new AdminNotFoundException("Admin not found")
		);
			adminName = admin.getAdminName();
		}
		ResignationDTO resignationDTO = new ResignationDTO(resignationView.getResignationReason(),resignationView.getResignationDate(),adminName,resignationView.getIsApproved());
		return resignationDTO;
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
	}

	public List<QualificationDetails> findQualificationDetails(Long id) throws EmployeeNotFoundException  {
         Employee employee = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No employee found")
		);
		return employee.getEmployeeDetails().getListQualificationDetails();
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

	public List<ProfDetails> findProfessionalDetails(Long id) throws EmployeeNotFoundException  {
         Employee employee = employeeRepository.findById(id).orElseThrow(
			     () -> new EmployeeNotFoundException("No employee found")
			 );
		return employee.getEmployeeDetails().getListProfDetails();
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
	}

	public List<FamilyDetails> findFamilyDetails(Long id) throws EmployeeNotFoundException  {
         Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("No details found")
			);
		return employee.getEmployeeDetails().getListFamilyDetails();
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

	public void deleteFamilyMemberById(Long empId, Long fid) throws FamilyDetailsNotFoundException, EmployeeNotFoundException {
		familyDetailsRepository.findById(fid).orElseThrow(
			() -> new FamilyDetailsNotFoundException("The family details is not found")
		);
		Employee employee  = employeeRepository.findById(empId).orElseThrow(
			     () -> new EmployeeNotFoundException("No details found")
		);
		boolean present = false;
		for(FamilyDetails fd : employee.getEmployeeDetails().getListFamilyDetails()){
			if(fd.getFamilyId() == fid){
				present = true;
			}
		}
		if (present){
			familyDetailsRepository.deleteById(fid);	
		}
		else{
			throw new AccessDeniedException("Access is denied");
		}
		
	}

	public void deleteProfessionById(Long empId, Long pid) throws ProfessionalDetailsNotFoundException, EmployeeNotFoundException {
		profDetailsRepository.findById(pid).orElseThrow(
			() -> new ProfessionalDetailsNotFoundException("The professional details is not found")
		);
		Employee employee  = employeeRepository.findById(empId).orElseThrow(
			     () -> new EmployeeNotFoundException("No employee found")
		);
		boolean present = false;
		for(ProfDetails pd : employee.getEmployeeDetails().getListProfDetails()){
			if(pd.getProfDelId() == pid){
				present = true;
			}
		}
		if (present){
			profDetailsRepository.deleteById(pid);	
		}
		else{
			throw new AccessDeniedException("Access is denied");
		}		
	}

	public void deleteQualificationById(Long empId, Long qid) throws QualificationDetailsNotFoundException, EmployeeNotFoundException {
		qualificationDetailsRepository.findById(qid).orElseThrow(
			() -> new QualificationDetailsNotFoundException("The qualification details is not found")
		);
		Employee employee  = employeeRepository.findById(empId).orElseThrow(
			     () -> new EmployeeNotFoundException("No employee found")
		);
		boolean present = false;
		for(QualificationDetails qd : employee.getEmployeeDetails().getListQualificationDetails()){
			if(qd.getQdId() == qid){
				present = true;
			}
		}
		if (present){
			qualificationDetailsRepository.deleteById(qid);	
		}
		else{
			throw new AccessDeniedException("Access is denied");
		}		
	}

	public LeaveLeftView findEmployeeLeavesLeft(Long id) throws EmployeeNotFoundException {
		employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("No employee found")
		);
		return employeeRepository.findLeaveLeft(id);
	}

	public void saveEmployeeDetails(Long id, EmployeeDetails employeeDetails) throws EmployeeNotFoundException, EmployeeDetailsNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new EmployeeNotFoundException("No employee found")
		);
		EmployeeDetails persistedEmployeeDetails = employee.getEmployeeDetails();
		if (persistedEmployeeDetails == null){
			throw new EmployeeDetailsNotFoundException("No details found");
		}
		employeeDetails.setListFamilyDetails(persistedEmployeeDetails.getListFamilyDetails());
		employeeDetails.setListProfDetails(persistedEmployeeDetails.getListProfDetails());
		employeeDetails.setListQualificationDetails(persistedEmployeeDetails.getListQualificationDetails());
		employeeDetails.setEmpDetId(persistedEmployeeDetails.getEmpDetId());

		employeeDetailsRepository.save(employeeDetails);
	}
}
