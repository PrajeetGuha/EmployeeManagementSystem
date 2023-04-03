package org.antwalk.ems.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.antwalk.ems.dto.EmployeeEditViewDTO;
import org.antwalk.ems.dto.LeaveApplicationListDTO;
import org.antwalk.ems.dto.ChangePasswordDTO;
import org.antwalk.ems.dto.EditDepartmentDTO;
import org.antwalk.ems.dto.EditProjectDTO;
import org.antwalk.ems.dto.EditTeamDTO;
import org.antwalk.ems.dto.NewDepartmentDTO;
import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.dto.NewProjectDTO;
import org.antwalk.ems.dto.NewTeamDTO;
import org.antwalk.ems.dto.ProjectListViewDTO;
import org.antwalk.ems.dto.ProjectPmDTO;
import org.antwalk.ems.dto.ResignationListDTO;
import org.antwalk.ems.dto.TeamListViewDTO;
import org.antwalk.ems.dto.TeamSelectionViewDTO;
import org.antwalk.ems.exception.AdminNotFoundException;
import org.antwalk.ems.exception.DepartmentNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.LeaveApplicationNotFoundException;
import org.antwalk.ems.exception.ProjectNotFoundException;
import org.antwalk.ems.exception.ResignationNotFoundException;
import org.antwalk.ems.exception.TeamNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.model.User;

import org.antwalk.ems.repository.AdminRepository;

import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.LeaveApplicationRepository;
import org.antwalk.ems.repository.ProjectRepository;
import org.antwalk.ems.repository.ResignationRepository;
import org.antwalk.ems.repository.TeamRepository;
import org.antwalk.ems.repository.UserRepository;
import org.antwalk.ems.view.EmployeeEditView;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.EmployeeSelectionView;
import org.antwalk.ems.view.LeaveApplicationAdminView;
import org.antwalk.ems.view.ProjectDetailsView;
import org.antwalk.ems.view.ProjectListView;
import org.antwalk.ems.view.ResignationListAdminView;
import org.antwalk.ems.view.TeamDetailsView;
import org.antwalk.ems.view.TeamListView;
import org.antwalk.ems.view.TeamSelectionView;
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
    LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MailService mailService;

    public Admin fetchAdminData(Long id) throws UserNotFoundException {
        return Optional.of(adminRepository.getById(id)).orElseThrow(
                () -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    public List<EmployeeListView> listEmployees(int pageNo, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, Sort.by("empId"));
        if (search.equals("null"))
            return employeeRepository.findAllEmployeeListViews(pageable).getContent();
        else
            return employeeRepository.findAllEmployeeListViewsWithSearch(pageable, search).getContent();
    }

    public int countPagesOfEmployees(String search) {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("empId"));
        if (search.equals("null"))
            return employeeRepository.findAll(pageable).getTotalPages();
        else
            return employeeRepository.findAllBySearch(pageable, search).getTotalPages();
    }

    public void deactivateEmp(Long empId) throws UserNotFoundException {
        if (employeeRepository.existsById(empId)) {
            employeeRepository.deactivateEmpById(empId);
            userRepository.disableUserById(empId);
            // mailService.sendDeactivationMail(employeeRepository.getWorkEmailByEmpId(empId),
            // employeeRepository.getEmpNameByEmpId(empId));
        } else {
            throw new UserNotFoundException("User with id: " + empId + " not found");
        }
    }

    public void activateEmp(Long empId) throws UserNotFoundException {
        if (employeeRepository.existsById(empId)) {
            employeeRepository.activateEmpById(empId);
            userRepository.enableUserById(empId);
            // mailService.sendActivationMail(employeeRepository.getWorkEmailByEmpId(empId),
            // employeeRepository.getEmpNameByEmpId(empId));
        } else {
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
    public void addEmployee(NewEmployeeDTO newEmployeeDTO) {

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmailId(newEmployeeDTO.getEmail());
        employeeDetailsRepository.save(employeeDetails);

        Employee employee = new Employee();
        employee.setEmpName(newEmployeeDTO.getName());
        employee.setDesignation(newEmployeeDTO.getDesignation());
        employee.setGender(newEmployeeDTO.getGender());
        employee.setYearOfExperience(newEmployeeDTO.getYearOfExperience());
        employee.setDepartment(newEmployeeDTO.getDepartment());

        employee.setGradeLevel(newEmployeeDTO.getGradeLevel());
        employee.setDoj(newEmployeeDTO.getDoj());
        employee.setEmptype(newEmployeeDTO.getEmptype());

        employee.setWorkEmail(newEmployeeDTO.getUsername() + "@nrifintech.com");
        employee.setEmployeeDetails(employeeDetails);
        Employee persistedEmployee = employeeRepository.save(employee);

        User user = new User();
        user.setTablePk(persistedEmployee.getEmpId());
        user.setIsEnabled(true);
        user.setPassword(passwordEncoder.encode(newEmployeeDTO.getPassword()));
        user.setTablePk(persistedEmployee.getEmpId());
        user.setUsername(newEmployeeDTO.getUsername());
        userRepository.save(user);

        // mailService.sendNewEmployeeMail(newEmployeeDTO.getPersonalEmail(),newEmployeeDTO.getName(),persistedEmployee.getWorkEmail(),newEmployeeDTO.getUsername(),newEmployeeDTO.getPassword());
    }

    public List<EmployeeSelectionView> listAllEmployees() {
        return employeeRepository.findAllEmployeeNames();
    }

    public List<String> listAllEmails() {
        return employeeDetailsRepository.listOfEmails();
    }

    public List<String> listAllUsernames() {
        return userRepository.listAllUsernames();
    }

    // public List<String> listDepartments() {
    // return departmentRepository.findAllDepartments();
    // }

    public List<Integer> findemployeecount() {
        return employeeRepository.findemployeecount();
    }

    public List<Integer> sexratio() {
        return employeeRepository.sexratio();
    }

    public List<Double> totalcost() {
        return employeeRepository.totalcost();
    }

    public List<Integer> emptype() {
        return employeeRepository.emptype();
    }

    public List<Integer> recruitment() {
        return employeeRepository.recruitment();
    }

    public List<String> getrecruitmentyear() {
        return employeeRepository.getrecruitmentyear();
    }

    public List<String> findProjectsWithTeams() {
        return projectRepository.findProjectsWithTeams();
    }

    public List<Integer> teamcount() {
        return teamRepository.teamcount();
    }

    public Long countAllEmployees() {
        return employeeRepository.ActiveEmployeeCount();
    }

    public List<String> deptname() {
        return employeeRepository.deptname();
    }

    public List<String> distgender() {
        return employeeRepository.distgender();
    }

    public List<String> distemptype() {
        return employeeRepository.distemptype();
    }

    public List<String> teamdept() {
        return teamRepository.teamdept();
    }

    // public Long countAllDepartments(){
    // return departmentRepository.count();
    // }

    // public int countPagesOfDepartments(){
    // Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("deptId"));
    // return departmentRepository.findAll(pageable).getTotalPages();
    // }

    public List<Team> getAllTeams(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, Sort.by("teamId"));
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
        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, Sort.by("projId"));
        return projectRepository.findAll(pageable).getContent();
    }

    public Long countAllProjects() {
        return projectRepository.count();
    }

    public int countPagesofProjects() {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("projId"));
        return projectRepository.findAll(pageable).getTotalPages();
    }
    // public void addDepartment(NewDepartmentDTO newDepartment) throws
    // EmployeeNotFoundException {
    // Department department = new Department();
    // if (newDepartment.getHod() == 0){
    // department.setHod(null);
    // }
    // else{
    // Employee employee =
    // employeeRepository.findById(newDepartment.getHod()).orElseThrow(
    // () -> new EmployeeNotFoundException("The employee not found.")
    // );
    // department.setHod(employee);
    // // employee.setDepartment(department);
    //
    // }
    // department.setDepartmentName(newDepartment.getDepartmentName());
    // Department persistedDepartment = departmentRepository.save(department);
    // if(newDepartment.getHod() != null){
    // Employee employee =
    // employeeRepository.findById(newDepartment.getHod()).orElseThrow(
    // () -> new EmployeeNotFoundException("The employee not found.")
    // );
    // employee.setDepartment(persistedDepartment);
    // employeeRepository.save(employee);
    // }
    // }

    public void changePassword(ChangePasswordDTO changePasswordDTO) throws UserNotFoundException {
        // retrieve user entity from database using empId
        User user = userRepository.findByTablePk(changePasswordDTO.getEmpId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // set new password for user
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getChangedPassword()));

        // save updated user entity to database
        userRepository.save(user);
    }

    public void leaveAction(Long lid, Long adminId, String approve)
            throws LeaveApplicationNotFoundException, AdminNotFoundException {
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(lid).orElseThrow(
                () -> new LeaveApplicationNotFoundException("Leave not found"));
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new AdminNotFoundException("admin not found"));
        leaveApplication.setAdmin(admin);
        leaveApplication.setIsApproved(approve.equals("true") ? true : false);
        Employee employee = leaveApplication.getEmployee();
        if (leaveApplication.getLeaveType().equals("CL") && leaveApplication.getIsApproved() == true) {

            employee.setClLeft(employee.getClLeft() - 1);
        } else if (leaveApplication.getLeaveType().equals("SL") && leaveApplication.getIsApproved() == true) {

            employee.setSlLeft(employee.getSlLeft() - 1);
        } else if (leaveApplication.getLeaveType().equals("PL") && leaveApplication.getIsApproved() == true) {

            employee.setMoreLeave(employee.getMoreLeave() + 1);
        }
        employeeRepository.save(employee);
        leaveApplication.setEmployee(employee);
        leaveApplicationRepository.save(leaveApplication);
    }

    public List<LeaveApplicationListDTO> listAllLeaves(int pg) throws EmployeeNotFoundException {
        Pageable pageable = PageRequest.of(pg - 1, PAGE_SIZE, Sort.by("applicationDate"));
        List<LeaveApplicationAdminView> listview = leaveApplicationRepository.findAllLeaves(pageable).getContent();
        List<LeaveApplicationListDTO> listviewDTO = new ArrayList<LeaveApplicationListDTO>();
        for (LeaveApplicationAdminView l : listview) {
            listviewDTO.add(new LeaveApplicationListDTO(l.getEmpLeaveId(), l.getLeaveType(),
                    employeeRepository.findById(l.getEmpId())
                            .orElseThrow(() -> new EmployeeNotFoundException("The employee is not found")).getEmpName(),
                    l.getLeaveReason(), l.getApplicationDate(), l.getIsApproved(), l.getLeaveAppliedFor()));
        }
        return listviewDTO;
    }

    public int totalLeaves() {
        return leaveApplicationRepository.findAll().size();
    }

    public int totalCountPagesLeaves() {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);
        return leaveApplicationRepository.findAll(pageable).getTotalPages();
    }

    public List<ResignationListDTO> listAllResignations(int pg) throws EmployeeNotFoundException {
        Pageable pageable = PageRequest.of(pg - 1, PAGE_SIZE);
        List<ResignationListAdminView> listview = resignationRepository.findAllResignations(pageable).getContent();
        List<ResignationListDTO> listviewDTO = new ArrayList<ResignationListDTO>();
        for (ResignationListAdminView r : listview) {
            listviewDTO.add(new ResignationListDTO(r.getResignationId(), r.getResignationReason(),
                    r.getResignationDate(), r.getIsApproved(),
                    employeeRepository.findById(r.getEmpId())
                            .orElseThrow(() -> new EmployeeNotFoundException("The employee is not found"))
                            .getEmpName()));
        }
        return listviewDTO;
        // return resignationRepository.findAll();
    }

    public int totalResignations() {
        return resignationRepository.findAll().size();
    }

    public int totalCountPagesResignations() {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);
        return resignationRepository.findAll(pageable).getTotalPages();
    }

    public void resignAction(Long rid, Long adminId, String approve)
            throws ResignationNotFoundException, AdminNotFoundException {
        Resignation resignation = resignationRepository.findById(rid).orElseThrow(
                () -> new ResignationNotFoundException("Resignation not found"));
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new AdminNotFoundException("admin not found"));
        resignation.setApprovedBy(admin);
        resignation.setIsApproved(approve.equals("true") ? true : false);
        resignationRepository.save(resignation);
    }

    // public void editDepartment(Long deptId, EditDepartmentDTO editDepartment)
    // throws Exception {
    // Department department = departmentRepository.findById(deptId).orElseThrow(
    // () -> new DepartmentNotFoundException("Department not found")
    // );
    // if (department.getEmployees() == null){
    // department.setEmployees(new ArrayList<Employee>());
    // }
    // department.setDepartmentName(editDepartment.getDepartmentName());
    // System.out.println(editDepartment);
    // System.out.println("\n\n\n");
    // if (editDepartment.getHod() == 0) {
    // department.setHod(null);
    // }
    // else {
    // Employee hod =
    // employeeRepository.findById(editDepartment.getHod()).orElseThrow(
    // () -> new EmployeeNotFoundException("Employee not found")
    // );
    // department.setHod(hod);
    // }
    // departmentRepository.save(department);
    // if (!editDepartment.getEmpList().equals("")) {
    // try{
    // List<String> employeeList =
    // Arrays.asList(editDepartment.getEmpList().split(", "));
    // for(String employee : employeeList){
    // Long id = Long.parseLong(employee.substring(1, employee.indexOf(")")));
    // // System.out.println(id);
    // Employee employeeToadd = employeeRepository.findById(id).orElseThrow(
    // () -> new EmployeeNotFoundException("Employee not found")
    // );
    // employeeToadd.setDepartment(department);
    // employeeRepository.save(employeeToadd);
    // }
    // }
    // catch(Exception e){
    // throw new Exception(e.getMessage());
    // }
    // }
    // }

    public void addTeam(NewTeamDTO newteam) throws EmployeeNotFoundException {
        Team team = new Team();

        team.setDepartment(newteam.getDepartment());

        team.setTeamName(newteam.getTeamName());
        teamRepository.save(team);

    }

    public void addTeamMembersToTeam(Long teamId, String teamMember)
            throws EmployeeNotFoundException, TeamNotFoundException {
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new TeamNotFoundException("Team not found"));
        team.setEmployees(new ArrayList<Employee>());
        List<String> employeeIdValues = Arrays.asList(teamMember.split(";"));
        if (team.getTm() != null) {
            if (!employeeIdValues.contains(Long.toString(team.getTm().getEmpId()))) {
                team.setTm(null);
            }
        }
        teamRepository.save(team);
        employeeRepository.updateTeam(teamId);
        if (!teamMember.equals("")) {
            List<String> employeeList = Arrays.asList(teamMember.split(";"));
            for (String employee : employeeList) {
                Long id = Long.parseLong(employee);
                Employee employeeToadd = employeeRepository.findById(id).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee not found"));
                employeeToadd.setTeam(team);
                employeeRepository.save(employeeToadd);
                System.out.println("Done");
            }
        }
    }

    public void editTeam(Long teamId, EditTeamDTO editTeam) throws Exception {
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new DepartmentNotFoundException("Team not found"));
        if (team.getEmployees() == null) {
            team.setEmployees(new ArrayList<Employee>());
        }
        team.setTeamName(editTeam.getTeamName());
        // System.out.println(editTeam);
        // System.out.println("\n\n\n");
        if (editTeam.getTm() == 0) {
            team.setTm(null);
        } else {
            Employee tm = employeeRepository.findById(editTeam.getTm()).orElseThrow(
                    () -> new EmployeeNotFoundException("Employee not found"));
            team.setTm(tm);
        }
        teamRepository.save(team);
        if (!editTeam.getEmpList().equals("")) {
            try {
                List<String> employeeList = Arrays.asList(editTeam.getEmpList().split(", "));
                for (String employee : employeeList) {
                    Long id = Long.parseLong(employee.substring(1, employee.indexOf(")")));
                    // System.out.println(id);
                    Employee employeeToadd = employeeRepository.findById(id).orElseThrow(
                            () -> new EmployeeNotFoundException("Employee not found"));
                    employeeToadd.setTeam(team);
                    employeeRepository.save(employeeToadd);
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    public void addProject(NewProjectDTO newProjectDTO) throws EmployeeNotFoundException {
        Project project = new Project();
        /*
         * if (newProjectDTO.getPm() == 0){ project.setPm(null); } else{ Employee
         * employee = employeeRepository.findById(newProjectDTO.getPm()).orElseThrow( ()
         * -> new EmployeeNotFoundException("The employee not found.") );
         * project.setPm(employee); // employee.setDepartment(department);
         * 
         * }
         */
        project.setProjectName(newProjectDTO.getProjectName());
        project.setStartDate(newProjectDTO.getStartDate());
        project.setEndDate(newProjectDTO.getEndDate());
        projectRepository.save(project);
    }

    /*
     * public void editProject(Long projectId, EditProjectDTO editProjectDTO) throws
     * Exception { Project project =
     * projectRepository.findById(projectId).orElseThrow( () -> new
     * DepartmentNotFoundException("Team not found") ); Set <Team> teams; if
     * (project.getTeams() == null){ teams = new HashSet<>(); } else { teams =
     * project.getTeams(); }
     * project.setProjectName(editProjectDTO.getProjectName()); //
     * System.out.println(editTeam); // System.out.println("\n\n\n"); if
     * (editProjectDTO.getPm() == 0) { project.setPm(null); } else { Employee pm =
     * employeeRepository.findById(editProjectDTO.getPm()).orElseThrow( () -> new
     * EmployeeNotFoundException("Employee not found") ); project.setPm(pm); }
     * 
     * if (editProjectDTO.getTeamList()!=null) { try{ List<String> teamList =
     * Arrays.asList(editProjectDTO.getTeamList().split(", ")); for(String team :
     * teamList){ Long id = Long.parseLong(team.substring(1, team.indexOf(")"))); //
     * System.out.println(id); Team teamToAdd =
     * teamRepository.findById(id).orElseThrow( () -> new
     * EmployeeNotFoundException("Team not found") ); teams.add(teamToAdd); } }
     * catch(Exception e){ throw new Exception(e.getMessage()); } }
     * project.setTeams(teams); projectRepository.save(project); }
     */

    public void saveEmployee(Long empId, Employee employee) throws EmployeeNotFoundException {
        Employee persistedEmployee = employeeRepository.findById(empId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found"));
        employee.setEmpId(empId);
        employee.setEmployeeDetails(persistedEmployee.getEmployeeDetails());
        employee.setTeam(persistedEmployee.getTeam());
        employee.setClLeft(persistedEmployee.getClLeft());
        employee.setPlLeft(persistedEmployee.getPlLeft());
        employee.setSlLeft(persistedEmployee.getSlLeft());
        employee.setMoreLeave(persistedEmployee.getMoreLeave());
        employee.setTotalLeave(persistedEmployee.getTotalLeave());
        employee.setWorkEmail(persistedEmployee.getWorkEmail());
        employee.setEmpstatus(persistedEmployee.getEmpstatus());
        employee.setResignation(persistedEmployee.getResignation());
        String before_dept = persistedEmployee.getDepartment();
        String after_dept = employee.getDepartment();
        employeeRepository.save(employee);
        if (!before_dept.equals(after_dept)) {
            employeeRepository.updateDepartment(empId);
            teamRepository.updateTM(empId);
        }

    }

    public Team findTeamById(Long tid) throws TeamNotFoundException {
        // TODO Auto-generated method stub
        return teamRepository.findById(tid).orElseThrow(
                () -> new TeamNotFoundException("Team Not Found"));
    }

    public List<EmployeeSelectionView> findEmployeesByDepartment(String department) {
        // TODO Auto-generated method stub
        return employeeRepository.findAllEmployeeNamesByDepartment(department);
    }

    public Project findProjectById(Long projid) throws ProjectNotFoundException {
        // TODO Auto-generated method stub
        return projectRepository.findById(projid).orElseThrow(
                () -> new ProjectNotFoundException("Project Not Found"));
    }

    public List<Team> findTeamsForProject() {
        // TODO Auto-generated method stub
        List<Team> teams = teamRepository.findAll();
        // for(int i = 0; i < teams.size(); i++) {
        // System.out.println(teams.get(i).getTeamId());
        // }
        return teams;
    }

    public List<EmployeeSelectionView> listAllPotentialTM(String department) {
        // TODO Auto-generated method stub
        return employeeRepository.findAllPotentialTM(department);
    }

    public List<EmployeeSelectionView> listAllPotentialPM() {
        // TODO Auto-generated method stub
        return employeeRepository.findAllPotentialPM();
    }

    public void addTeamManagerToTeam(Long tid, Long tm) throws EmployeeNotFoundException, TeamNotFoundException {
        Team teamToAdd = teamRepository.findById(tid).orElseThrow(
                () -> new TeamNotFoundException("Team not found"));
        Employee persistedEmployee = employeeRepository.findById(tm).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found"));
        teamToAdd.setTm(persistedEmployee);
        persistedEmployee.setTeam(teamToAdd);
        employeeRepository.save(persistedEmployee);
        teamRepository.save(teamToAdd);

    }

    public void addTeamsToProject(Long projId, String teamMember)
            throws ProjectNotFoundException, TeamNotFoundException {
        Project project = projectRepository.findById(projId).orElseThrow(
                () -> new ProjectNotFoundException("Project not found"));
        project.setTeams(new ArrayList<Team>());

        List<String> employeeIdValues = Arrays.asList(teamMember.split(";"));
        if (project.getPm() != null) {
            if (!employeeIdValues.contains(Long.toString(project.getPm().getEmpId()))) {
                project.setPm(null);
            }
        }
        List<Team> AllTeamsInAProject = new ArrayList<Team>();
        if (!teamMember.equals("")) {
            List<String> teamList = Arrays.asList(teamMember.split(";"));
            for (String employee : teamList) {
                Long id = Long.parseLong(employee);
                Team teamToadd = teamRepository.findById(id).orElseThrow(
                        () -> new TeamNotFoundException("Team not found"));
                AllTeamsInAProject.add(teamToadd);
                System.out.println("Done");
            }
            project.setTeams(AllTeamsInAProject);
        }
        projectRepository.save(project);
    }

    public void addProjectManagerToProject(Long projid, ProjectPmDTO projectPm)
            throws ProjectNotFoundException, EmployeeNotFoundException {
        if (projectPm.getPm() == 0) {
            return;
        } else {
            Project pmToAdd = projectRepository.findById(projid).orElseThrow(
                    () -> new ProjectNotFoundException("Project not found"));
            Employee pm = employeeRepository.findById(projectPm.getPm()).orElseThrow(
                    () -> new EmployeeNotFoundException("Employee not found"));

            pmToAdd.setPm(pm);
            projectRepository.save(pmToAdd);
        }
    }

    public List<Integer> findTeamCountByProject() {
        // TODO Auto-generated method stub
        return projectRepository.findTeamCountByProject();
    }

    public Employee findEmployeeById(Long id_val) throws EmployeeNotFoundException {
        return employeeRepository.findById(id_val).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found"));
    }

    public List<TeamSelectionView> getAllTeams() {

        return teamRepository.getAllTeams();
    }

    public List<TeamListViewDTO> getTeamDetails(int pageNo) throws EmployeeNotFoundException {
        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE);
        List<TeamListView> listview = teamRepository.getTeamDetails(pageable).getContent();
        System.out.println(listview.size() + "/n/n/n/n/n");
        List<TeamListViewDTO> listview2 = new ArrayList<>();
        for (int i = 0; i < listview.size(); i++) {
            TeamListViewDTO teamListVal;
            if (listview.get(i).getTm() != null) {
                Long tmId = listview.get(i).getTm();
                Employee tmValue = employeeRepository.findById(tmId).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee not found"));
                teamListVal = new TeamListViewDTO(listview.get(i).getTeamId(), listview.get(i).getTeamName(),
                        listview.get(i).getDepartment(), tmValue.getEmpName());

            } else {
                teamListVal = new TeamListViewDTO(listview.get(i).getTeamId(), listview.get(i).getTeamName(),
                        listview.get(i).getDepartment(), "");
            }
            listview2.add(teamListVal);

        }
        return listview2;
    }

    public List<ProjectListViewDTO> getProjectDetails(int pageNo) throws EmployeeNotFoundException {

        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE);
        List<ProjectListView> listview = projectRepository.getProjectDetails(pageable).getContent();
        System.out.println(listview.size() + "/n/n/n/n/n");
        List<ProjectListViewDTO> listview2 = new ArrayList<>();
        for (int i = 0; i < listview.size(); i++) {
            ProjectListViewDTO projectListVal;
            if (listview.get(i).getPm() != null) {
                Long pmId = listview.get(i).getPm();
                Employee pmValue = employeeRepository.findById(pmId).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee not found"));
                projectListVal = new ProjectListViewDTO(listview.get(i).getProjId(), listview.get(i).getProjectName(),
                        listview.get(i).getStartDate(), listview.get(i).getEndDate(), pmValue.getEmpName());

            } else {
                projectListVal = new ProjectListViewDTO(listview.get(i).getProjId(), listview.get(i).getProjectName(),
                        listview.get(i).getStartDate(), listview.get(i).getEndDate(), "");
            }
            listview2.add(projectListVal);

        }
        return listview2;
    }

    public void deleteProjectById(Long projId) throws ProjectNotFoundException {
        // TODO Auto-generated method stub
        projectRepository.findById(projId).orElseThrow(
                () -> new ProjectNotFoundException("Project not found"));

        projectRepository.deleteById(projId);

    }

    public void deleteTeamById(Long teamId) throws TeamNotFoundException {
        Team teamToadd = teamRepository.findById(teamId).orElseThrow(
                () -> new TeamNotFoundException("Team not found"));
        if (teamToadd.getTm() != null) {
            employeeRepository.modifyEmployeeForTeamByEmpId(teamToadd.getTm().getEmpId());
        }
        teamRepository.deleteById(teamId);
        employeeRepository.modifyEmployeeForTeam(teamId);
    }

    public EmployeeEditViewDTO getEmployeeEditView(Long id_val)
            throws EmployeeNotFoundException, TeamNotFoundException {
        EmployeeEditView employee = Optional.of(employeeRepository.getEmployeeEditView(id_val))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        EmployeeEditViewDTO employeeDTO = new EmployeeEditViewDTO(id_val, employee.getEmpName(), employee.getGender(),
                employee.getGradeLevel(), employee.getDoj(), employee.getDesignation(), employee.getEmptype(),
                employee.getProbPeriod(), employee.getProbCompDate(), employee.getTrainPeriod(),
                employee.getContractEndDate(), employee.getServPeriod(), employee.getWorkEmail(), employee.getBranch(),
                employee.getOffice(), employee.getWorkstationId(), employee.getCtc(), employee.getYearOfExperience(),
                employee.getDepartment(),
                (employee.getTeam() == null ? null
                        : teamRepository.findById(employee.getTeam()).orElseThrow(
                                () -> new TeamNotFoundException("Team not found")).getTeamName()));

        return employeeDTO;
    }

    public List<EmployeeSelectionView> findEmployeesInTeamById(Long tid) throws TeamNotFoundException {
        teamRepository.findById(tid).orElseThrow(
                () -> new TeamNotFoundException("Team not found"));
        return employeeRepository.findEmployeesWithTeam(tid);
    }

    public TeamDetailsView getTeamDetailsById(Long tid) throws TeamNotFoundException {
        teamRepository.findById(tid).orElseThrow(
                () -> new TeamNotFoundException("Team not found"));
        return teamRepository.findTeamDetails(tid);
    }

    public List<TeamSelectionViewDTO> findTeamsInProjectById(Long projid) throws ProjectNotFoundException {
        projectRepository.findById(projid).orElseThrow(
                () -> new ProjectNotFoundException("Project not found"));
        List<Long> teamids = projectRepository.findTeamIdsWithProjectId(projid);
        List<TeamSelectionViewDTO> listview = new ArrayList<>();
        for(Long id : teamids){
            listview.add(new TeamSelectionViewDTO(id,teamRepository.findTeamNameById(id)));
        }
        return listview;
    }

    public ProjectDetailsView getProjectDetailsById(Long projid) throws ProjectNotFoundException {
        projectRepository.findById(projid).orElseThrow(
                () -> new ProjectNotFoundException("Project not found"));
        return projectRepository.getProjectDetailsById(projid);
    }
}
