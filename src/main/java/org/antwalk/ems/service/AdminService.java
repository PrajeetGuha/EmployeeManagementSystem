package org.antwalk.ems.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.antwalk.ems.dto.ChangePasswordDTO;
import org.antwalk.ems.dto.EditDepartmentDTO;
import org.antwalk.ems.dto.EditProjectDTO;
import org.antwalk.ems.dto.EditTeamDTO;
import org.antwalk.ems.dto.NewDepartmentDTO;
import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.dto.NewProjectDTO;
import org.antwalk.ems.dto.NewTeamDTO;
import org.antwalk.ems.exception.DepartmentNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
//import org.antwalk.ems.model.Department;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.AdminRepository;
//import org.antwalk.ems.repository.DepartmentRepository;
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

//    @Autowired
//    DepartmentRepository departmentRepository;
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

            employee.setDepartment(newEmployeeDTO.getDepartment());
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

//    public List<String> listDepartments() {
//        return departmentRepository.findAllDepartments();
//    }

    
//  public List<Integer> employeesInDepartment()
//  {
//	  return departmentRepository.findemployeecount();
//  }
  public List<Integer> sexratio()
  {
	  return employeeRepository.sexratio();
  }
  
//  public List<Double> totalcost()
//  {
//	  return departmentRepository.totalcost();
//  }
  public List<String> emptype()
  {
	  return employeeRepository.emptype();
  }
  public List<Integer> recruitment()
  {
	  return employeeRepository.recruitment();
  }
//  public List<String> teamsinprojects()
//  {
//	  return projectRepository.teamsinprojects();
//  }
//  public List<Integer> findteamcount()
//  {
//	  return projectRepository.findteamcount();
//  }

//    public List<Department> getAllDepartments(int pageNo){
//        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("deptId"));
//        return departmentRepository.findAll(pageable).getContent();
//    }
//    
    public Long countAllEmployees(){
        return employeeRepository.count();
    }

//    public Long countAllDepartments(){
//        return departmentRepository.count();
//    }
    
//    public int countPagesOfDepartments(){
//        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("deptId"));
//        return departmentRepository.findAll(pageable).getTotalPages();
//    }

    public List<Team> getAllTeams(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("teamId"));
        return teamRepository.findAll(pageable).getContent();
    }
    
    public List<Team> getAllTeamsdetails() {
        
        return teamRepository.findAll();
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
//    public void addDepartment(NewDepartmentDTO newDepartment) throws EmployeeNotFoundException {
//        Department department = new Department();
//        if (newDepartment.getHod() == 0){
//            department.setHod(null);
//        }
//        else{
//            Employee employee = employeeRepository.findById(newDepartment.getHod()).orElseThrow(
//            () -> new EmployeeNotFoundException("The employee not found.")
//            );
//            department.setHod(employee);
//            // employee.setDepartment(department);
//
//        }
//        department.setDepartmentName(newDepartment.getDepartmentName());
//        Department persistedDepartment = departmentRepository.save(department);
//        if(newDepartment.getHod() != null){
//            Employee employee = employeeRepository.findById(newDepartment.getHod()).orElseThrow(
//            () -> new EmployeeNotFoundException("The employee not found.")
//            );
//            employee.setDepartment(persistedDepartment);
//            employeeRepository.save(employee);
//        }
//    }
	
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
        
//        public void editDepartment(Long deptId, EditDepartmentDTO editDepartment) throws Exception {
//            Department department = departmentRepository.findById(deptId).orElseThrow(
//                () -> new DepartmentNotFoundException("Department not found")
//            );
//            if (department.getEmployees() == null){
//                department.setEmployees(new ArrayList<Employee>());
//            }
//            department.setDepartmentName(editDepartment.getDepartmentName());
//            System.out.println(editDepartment);
//            System.out.println("\n\n\n");
//            if (editDepartment.getHod() == 0) {
//            	department.setHod(null);
//            }
//            else {
//            	 Employee hod = employeeRepository.findById(editDepartment.getHod()).orElseThrow(
//                         () -> new EmployeeNotFoundException("Employee not found")
//                     );
//            	department.setHod(hod);
//            }
//            departmentRepository.save(department);
//           if (!editDepartment.getEmpList().equals("")) {
//        	   try{
//                   List<String> employeeList = Arrays.asList(editDepartment.getEmpList().split(", "));
//                   for(String employee : employeeList){
//                       Long id = Long.parseLong(employee.substring(1, employee.indexOf(")")));
//                       // System.out.println(id);
//                       Employee employeeToadd = employeeRepository.findById(id).orElseThrow(
//                           () -> new EmployeeNotFoundException("Employee not found")
//                       );
//                       employeeToadd.setDepartment(department);
//                       employeeRepository.save(employeeToadd);
//                   }
//               }
//               catch(Exception e){
//                   throw new Exception(e.getMessage());
//               }
//           }
//        }
        
        
        
        
        public void addTeam(NewTeamDTO newteam) throws EmployeeNotFoundException {
            Team team = new Team();
            System.out.println(newteam.getTm());
            System.out.println(newteam.getTm() == 0);
            if (newteam.getTm() == 0){
                team.setTm(null);
            }
            else{
                Employee employee = employeeRepository.findById(newteam.getTm()).orElseThrow(
                () -> new EmployeeNotFoundException("The employee not found.")
                );
                team.setTm(employee);
                // employee.setDepartment(department);

            }
            team.setTeamName(newteam.getTeamName());
            Team persistedTeam = teamRepository.save(team);
            if(newteam.getTm() != 0){
                Employee employee = employeeRepository.findById(newteam.getTm()).orElseThrow(
                () -> new EmployeeNotFoundException("The employee not found.")
                );
                employee.setTeam(persistedTeam);
                employeeRepository.save(employee);
            }
        }
        
        public void editTeam(Long teamId, EditTeamDTO editTeam) throws Exception {
            Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new DepartmentNotFoundException("Team not found")
            );
            if (team.getEmployees() == null){
            	team.setEmployees(new ArrayList<Employee>());
            }
            team.setTeamName(editTeam.getTeamName());
//            System.out.println(editTeam);
//            System.out.println("\n\n\n");
            if (editTeam.getTm() == 0) {
            	team.setTm(null);
            }
            else {
            	 Employee tm = employeeRepository.findById(editTeam.getTm()).orElseThrow(
                         () -> new EmployeeNotFoundException("Employee not found")
                     );
            	team.setTm(tm);
            }
            teamRepository.save(team);
           if (!editTeam.getEmpList().equals("")) {
        	   try{
                   List<String> employeeList = Arrays.asList(editTeam.getEmpList().split(", "));
                   for(String employee : employeeList){
                       Long id = Long.parseLong(employee.substring(1, employee.indexOf(")")));
                       // System.out.println(id);
                       Employee employeeToadd = employeeRepository.findById(id).orElseThrow(
                           () -> new EmployeeNotFoundException("Employee not found")
                       );
                       employeeToadd.setTeam(team);
                       employeeRepository.save(employeeToadd);
                   }
               }
               catch(Exception e){
                   throw new Exception(e.getMessage());
               }
           }
        }
        
        
        public void addProject(NewProjectDTO newProjectDTO) throws EmployeeNotFoundException {
            Project project = new Project();
            if (newProjectDTO.getPm() == 0){
            	project.setPm(null);
            }
            else{
                Employee employee = employeeRepository.findById(newProjectDTO.getPm()).orElseThrow(
                () -> new EmployeeNotFoundException("The employee not found.")
                );
                project.setPm(employee);
                // employee.setDepartment(department);

            }
            project.setProjectName(newProjectDTO.getProjectName());
            project.setStartDate(newProjectDTO.getStartDate());
            project.setEndDate(newProjectDTO.getEndDate());
            projectRepository.save(project);
        }
        
        

        
        public void editProject(Long projectId, EditProjectDTO editProjectDTO) throws Exception {
            Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new DepartmentNotFoundException("Team not found")
            );
            Set <Team> teams;
            if (project.getTeams() == null){
            	teams = new HashSet<>();
            }
            else {
            	teams = project.getTeams();
			}
            project.setProjectName(editProjectDTO.getProjectName());
//            System.out.println(editTeam);
//            System.out.println("\n\n\n");
            if (editProjectDTO.getPm() == 0) {
            	project.setPm(null);
            }
            else {
            	 Employee pm = employeeRepository.findById(editProjectDTO.getPm()).orElseThrow(
                         () -> new EmployeeNotFoundException("Employee not found")
                     );
            	project.setPm(pm);
            }
            
           if (editProjectDTO.getTeamList()!=null) {
        	   try{
                   List<String> teamList = Arrays.asList(editProjectDTO.getTeamList().split(", "));
                   for(String team : teamList){
                       Long id = Long.parseLong(team.substring(1, team.indexOf(")")));
                       // System.out.println(id);
                       Team teamToAdd = teamRepository.findById(id).orElseThrow(
                           () -> new EmployeeNotFoundException("Team not found")
                       );
                       teams.add(teamToAdd);
                   }
               }
               catch(Exception e){
                   throw new Exception(e.getMessage());
               }
           }
           project.setTeams(teams);
           projectRepository.save(project);
        }
        
        
}
