package org.antwalk.ems.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Department;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.DepartmentRepository;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.ProjectRepository;
import org.antwalk.ems.repository.TeamRepository;
import org.antwalk.ems.view.EmployeeListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.antwalk.ems.repository.UserRepository;

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
    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

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
    public void addEmployee(NewEmployeeDTO newEmployeeDTO){

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmailId(newEmployeeDTO.getPersonalEmail());
        employeeDetailsRepository.save(employeeDetails);

        Employee employee = new Employee();
        employee.setEmpName(newEmployeeDTO.getName());
        employee.setWorkEmail(newEmployeeDTO.getUsername()+"@nrifintech.com");
        employee.setEmployeeDetails(employeeDetails);
        Employee persistedEmployee = employeeRepository.save(employee);
        
        User user = new User();
        user.setTablePk(persistedEmployee.getEmpId());
        user.setIsEnabled(true);
        user.setPassword(passwordEncoder.encode(newEmployeeDTO.getPassword()));
        user.setTablePk(persistedEmployee.getEmpId());
        user.setUsername(newEmployeeDTO.getUsername());
        User persistedUser = userRepository.save(user);

        //mailService.sendNewEmployeeMail(newEmployeeDTO.getPersonalEmail(),newEmployeeDTO.getName(),persistedEmployee.getWorkEmail(),newEmployeeDTO.getUsername(),newEmployeeDTO.getPassword());
    }
    
    public List<String> listAllEmployees(){
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

    public List<Department> getAllDepartments(int pageNo){
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("deptId"));
        return departmentRepository.findAll(pageable).getContent();
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
        return null;
    }

    public Long countAllProjects() {
        return null;
    }

    public int countPagesofProjects() {
        return 0;
    }


    
}
