package org.antwalk.ems.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.antwalk.ems.dto.ChangePasswordDTO;
import org.antwalk.ems.dto.NewDepartmentDTO;
import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.exception.DepartmentNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Department;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.DepartmentRepository;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
import org.antwalk.ems.repository.ProjectRepository;
import org.antwalk.ems.repository.ResignationRepository;
import org.antwalk.ems.repository.TeamRepository;
import org.antwalk.ems.repository.UserRepository;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.EmployeeSelectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository UserRepository;

    @Value("${fetch.pagesize}")
    private int PAGE_SIZE;

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    ResignationRepository resignationRepository;

    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MailService mailService;

    public Admin fetchAdminData(Long id) throws UserNotFoundException{
        return Optional.of(adminRepository.getById(id)).orElseThrow(
            () -> new UserNotFoundException("User with id: " + id + " not found")
        );
    }
  

    public List<EmployeeListView> listEmployees(int pageNo, String search){
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("empId"));
        if (search.equals("null"))
            return employeeRepository.findAllEmployeeListViews(pageable).getContent();
        else
            return employeeRepository.findAllEmployeeListViewsWithSearch(pageable,search).getContent();
    }

    public int countPagesOfEmployees(String search){
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("empId"));
        if (search.equals("null"))
            return employeeRepository.findAll(pageable).getTotalPages();
        else
            return employeeRepository.findAllBySearch(pageable,search).getTotalPages();
    }

    public void deactivateEmp(Long empId) throws UserNotFoundException{
        if (employeeRepository.existsById(empId)){
            employeeRepository.deactivateEmpById(empId);
            userRepository.disableUserById(empId);
            //mailService.sendDeactivationMail(employeeRepository.getWorkEmailByEmpId(empId), employeeRepository.getEmpNameByEmpId(empId));
        }
        else{
            throw new UserNotFoundException("User with id: " + empId + " not found");
        }
    }

    public void activateEmp(Long empId) throws UserNotFoundException{
        if (employeeRepository.existsById(empId)){
            employeeRepository.activateEmpById(empId);
            userRepository.enableUserById(empId);
            //mailService.sendActivationMail(employeeRepository.getWorkEmailByEmpId(empId), employeeRepository.getEmpNameByEmpId(empId));
        }
        else{
            throw new UserNotFoundException("User with id: " + empId + " not found");
        }
    }
    
    public long countEmployees(String search) {
        if (search.equals("null"))
    	    return employeeRepository.count();
        else
            return employeeRepository.countBySearch(search);
    }

    @Transactional
    public void addEmployee(NewEmployeeDTO newEmployeeDTO) throws DepartmentNotFoundException{

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmailId(newEmployeeDTO.getEmail());
        employeeDetailsRepository.save(employeeDetails);

        Employee employee = new Employee();
        employee.setEmpName(newEmployeeDTO.getName());
        employee.setDesignation(newEmployeeDTO.getDesignation());
        employee.setGender(newEmployeeDTO.getGender());
        if (newEmployeeDTO.getDepartment().equals("0")){
            employee.setDepartment(null);
        }
        else{
            Department department = departmentRepository.findByDepartmentName(newEmployeeDTO.getDepartment()).orElseThrow(
                () -> new DepartmentNotFoundException("Department with name" + newEmployeeDTO.getDepartment() + " not found")
            );
            employee.setDepartment(department);
        }
        employee.setGradeLevel(newEmployeeDTO.getGradeLevel());
        employee.setDoj(newEmployeeDTO.getDoj());
        employee.setEmptype(newEmployeeDTO.getEmptype());

        employee.setWorkEmail(newEmployeeDTO.getUsername()+"@nrifintech.com");
        employee.setEmployeeDetails(employeeDetails);
        Employee persistedEmployee = employeeRepository.save(employee);
        
        User user = new User();
        user.setTablePk(persistedEmployee.getEmpId());
        user.setIsEnabled(true);
        user.setPassword(passwordEncoder.encode(newEmployeeDTO.getPassword()));
        user.setTablePk(persistedEmployee.getEmpId());
        user.setUsername(newEmployeeDTO.getUsername());
        userRepository.save(user);

        //mailService.sendNewEmployeeMail(newEmployeeDTO.getPersonalEmail(),newEmployeeDTO.getName(),persistedEmployee.getWorkEmail(),newEmployeeDTO.getUsername(),newEmployeeDTO.getPassword());
    }  
    public List<EmployeeSelectionView> listAllEmployees(){
        return employeeRepository.findAllEmployeeNames();
    }
  

    public List<String> listAllEmails(){
        return employeeDetailsRepository.listOfEmails();
    }

    public List<String> listAllUsernames(){
        return userRepository.listAllUsernames();
    }

    public List<String> listDepartments() {
        return departmentRepository.findAllDepartments();
    }

    
  public List<Integer> employeesInDepartment()
  {
	  return departmentRepository.findemployeecount();
  }
  public List<Integer> sexratio()
  {
	  return departmentRepository.sexratio();
  }
  
  public List<Double> totalcost()
  {
	  return departmentRepository.totalcost();
  }

    public List<Department> getAllDepartments(int pageNo){
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("deptId"));
        return departmentRepository.findAll(pageable).getContent();
    }
    
    public Long countAllEmployees(){
        return employeeRepository.count();
    }

    public Long countAllDepartments(){
        return departmentRepository.count();
    }
    
    public int countPagesOfDepartments(){
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("deptId"));
        return departmentRepository.findAll(pageable).getTotalPages();
    }

    public List<Team> getAllTeams(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("teamId"));
        return teamRepository.findAll(pageable).getContent();
    }

    public Long countAllTeams() {
        return teamRepository.count();
    }

    public int countPagesofTeams() {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("teamId"));
        return teamRepository.findAll(pageable).getTotalPages();
    }

    public List<Project> getAllProjects(int pageNo) {
    	Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("projId"));
        return projectRepository.findAll(pageable).getContent();
    }

    public Long countAllProjects() {
    	return projectRepository.count();
    }

    public int countPagesofProjects() {
    	Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("projId"));
    	return projectRepository.findAll(pageable).getTotalPages();
    }
    public void addDepartment(NewDepartmentDTO newDepartment) throws EmployeeNotFoundException {
        Department department = new Department();
        if (newDepartment.getHod() == 0){
            newDepartment.setHod(null);
        }
        else{
            Employee employee = employeeRepository.findById(newDepartment.getHod()).orElseThrow(
            () -> new EmployeeNotFoundException("The employee not found.")
            );
            department.setHod(employee);
            // employee.setDepartment(department);

        }
        department.setDepartmentName(newDepartment.getDepartmentName());
        Department persistedDepartment = departmentRepository.save(department);
        if(newDepartment.getHod() != null){
            Employee employee = employeeRepository.findById(newDepartment.getHod()).orElseThrow(
            () -> new EmployeeNotFoundException("The employee not found.")
            );
            employee.setDepartment(persistedDepartment);
            employeeRepository.save(employee);
        }
    }
	
	public void changePassword(ChangePasswordDTO changePasswordDTO) throws RuntimeException {
	    // retrieve user entity from database using empId
	    User user = userRepository.findByTablePk(changePasswordDTO.getEmpId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // set new password for user
	    System.out.println("\n\n\n\n\n\n\n\n\n"+user.getPassword()+"\n\n\n\n\n\n\n\n\n\n");
	    user.setPassword(passwordEncoder.encode(changePasswordDTO.getChangedPassword()));

	    // save updated user entity to database
	    userRepository.save(user);
	}
    public void leaveAction(Long lid, Long adminId, String approve) throws Exception {
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(lid).orElseThrow(
            () -> new Exception("Leave not found")
        );
        Admin admin = adminRepository.findById(adminId).orElseThrow(
        () -> new Exception("admin not found")
        );
        leaveApplication.setAdmin(admin);
        leaveApplication.setIsApproved(approve.equals("true") ? true : false);
        leaveApplicationRepository.save(leaveApplication);
        }
        
        
        public List<LeaveApplication> listAllLeaves(int pg) {
            Pageable pageable = PageRequest.of(pg, PAGE_SIZE, Sort.by("applicationDate"));
            return leaveApplicationRepository.findAll();
        }


        public List<Resignation> listAllResignations(int pg) {
            Pageable pageable = PageRequest.of(pg, PAGE_SIZE);
//            return resignationRepository.findAllRecentResignations(pageable).getContent();
            return resignationRepository.findAll();
        }

        public void resignAction(Long rid, Long adminId, String approve) throws Exception {
            Resignation resignation = resignationRepository.findById(rid).orElseThrow(
                () -> new Exception("Resign not found")
            );
            Admin admin = adminRepository.findById(adminId).orElseThrow(
            () -> new Exception("admin not found")
            );
            resignation.setApprovedBy(admin);
            resignation.setIsApproved(approve.equals("true") ? true : false);
            resignationRepository.save(resignation);
            }
}
