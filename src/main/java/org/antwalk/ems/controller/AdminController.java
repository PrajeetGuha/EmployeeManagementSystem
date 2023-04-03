package org.antwalk.ems.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antwalk.ems.dto.ChangePasswordDTO;
import org.antwalk.ems.dto.EditDepartmentDTO;
import org.antwalk.ems.dto.EditProjectDTO;
import org.antwalk.ems.dto.EditTeamDTO;
import org.antwalk.ems.dto.EmployeeEditViewDTO;
import org.antwalk.ems.dto.LeaveApplicationListDTO;
import org.antwalk.ems.dto.NewDepartmentDTO;
import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.dto.NewProjectDTO;
import org.antwalk.ems.dto.NewTeamDTO;
import org.antwalk.ems.dto.ProjectListViewDTO;
import org.antwalk.ems.dto.ProjectPmDTO;
import org.antwalk.ems.dto.ResignationListDTO;
import org.antwalk.ems.dto.TeamListViewDTO;
import org.antwalk.ems.dto.TeamSelectionViewDTO;
import org.antwalk.ems.exception.DepartmentNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.ProjectNotFoundException;
import org.antwalk.ems.exception.TeamNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.pojo.SuccessDetails;

import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.antwalk.ems.service.AdminService;
import org.antwalk.ems.service.ReportService;
import org.antwalk.ems.view.EmployeeEditView;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.EmployeeSelectionView;
import org.antwalk.ems.view.ProjectDetailsView;
import org.antwalk.ems.view.ProjectListView;
import org.antwalk.ems.view.ResignationListAdminView;
import org.antwalk.ems.view.TeamDetailsView;
import org.antwalk.ems.view.TeamListView;
import org.antwalk.ems.view.TeamSelectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @RestController
// @RequestMapping("/dashboard/admin")

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ReportService reportService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("editemployeedetails")
    public String editemployeedetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("empId");
        String search = request.getParameter("search");
        String pg = request.getParameter("pg");
        Long id_val = Long.parseLong(id);
        Long addid = AuthenticationSystem.getId();
        String status;
        Admin admin;
        EmployeeEditViewDTO employee;
        try {
            admin = adminService.fetchAdminData(addid);
            status = "SUCCESS";
        } catch (Exception e) {
            admin = new Admin();
            status = "FAILED";
        }
        try {
            employee = adminService.getEmployeeEditView(id_val);
            status = "SUCCESS";
        } catch (Exception e) {
            employee = new EmployeeEditViewDTO();
            status = "FAILED";
        }
        model.addAttribute("status", status);
        model.addAttribute("admin", admin);
        model.addAttribute("employee", employee); /////
        model.addAttribute("search", search);
        model.addAttribute("pg", pg);
        return "editUser";
    }

    @GetMapping("/analytics")

    public String analytics(HttpServletRequest request, Model model) throws UserNotFoundException {

        Long id = AuthenticationSystem.getId();
        Admin admin;
        String status;
        try {
            admin = adminService.fetchAdminData(id);
            status = "SUCCESS";
        } catch (Exception e) {
            admin = new Admin();
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        Long count = adminService.countAllEmployees();
        Long countteam = adminService.countAllTeams();
        Long countproject = adminService.countAllProjects();

        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
        List<Integer> findemployeecount = adminService.findemployeecount();
        List<Integer> sexratio = adminService.sexratio();

        List<Integer> findteamcount = adminService.findTeamCountByProject();
        List<Double> totalcost = adminService.totalcost();
        List<Integer> emptype = adminService.emptype();
        List<Integer> recruitment = adminService.recruitment();

        List<Integer> teamcount = adminService.teamcount();
        List<String> deptname = adminService.deptname();
        List<String> teamdept = adminService.teamdept();

        List<String> distgender = adminService.distgender();
        List<String> distemptype = adminService.distemptype();
        List<String> getrecruitmentyear = adminService.getrecruitmentyear();
        List<String> findProjectsWithTeams = adminService.findProjectsWithTeams();

        model.addAttribute("status", status);
        model.addAttribute("admin", admin);
        model.addAttribute("countOfEmployees", count);
        model.addAttribute("countOfTeams", countteam);
        model.addAttribute("countOfProjects", countproject);
        model.addAttribute("allemployeenames", allemployees);
        model.addAttribute("findemployeecount", findemployeecount);
        model.addAttribute("findteamcount", findteamcount);

        model.addAttribute("sexratio", sexratio);
        model.addAttribute("distgender", distgender);
        model.addAttribute("distemptype", distemptype);
        model.addAttribute("getrecruitmentyear", getrecruitmentyear);
        model.addAttribute("findProjectsWithTeams", findProjectsWithTeams);
        model.addAttribute("totalcost", totalcost);
        model.addAttribute("emptype", emptype);
        model.addAttribute("recruitment", recruitment);
        model.addAttribute("teamcount", teamcount);
        model.addAttribute("deptname", deptname);
        model.addAttribute("teamdept", teamdept);

        return "analytics";
    }

    @GetMapping("dashboard")
    public String admindashboard(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        String search = request.getParameter("search");
        Admin admin = null;
        String status;
        try {
            admin = adminService.fetchAdminData(id);
            status = "SUCCESS";
        } catch (Exception e) {
            admin = new Admin();
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        int pageCount = adminService.countPagesOfEmployees(search);
        Long empCount = adminService.countEmployees(search);
        List<String> usernames = adminService.listAllUsernames();
        List<String> emailIds = adminService.listAllEmails();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
        List<EmployeeListView> employeeListViews = adminService.listEmployees(pageNo, search);
        model.addAttribute("admin", admin);
        model.addAttribute("employees", employeeListViews);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("empCount", empCount);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames", allemployees);

        model.addAttribute("usernames", usernames);
        model.addAttribute("emailIds", emailIds);
        model.addAttribute("search", search);
        model.addAttribute("status", status);

        return "admindashboard";
    }

    @GetMapping("report")
    public void generateEmployeeReport(Model model, HttpServletResponse response, HttpServletRequest request) {
        Long empId = Long.parseLong(request.getParameter("empId"));
        try {
            reportService.generateEmployeeReport(response, empId);
        } catch (Exception e) {
        }
    }

    @GetMapping("/projectallocation")
    public String projectallocation(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        List<ProjectListViewDTO> listProjects = null;
        Admin admin = null;
        String status = null;
        try {
            listProjects = adminService.getProjectDetails(pageNo);
        } catch (Exception e1) {
            model.addAttribute("exception", e1);
            status = "FAILED";
        }
        try {
            admin = adminService.fetchAdminData(id);
            if (status == null) {
                status = "SUCCESS";
            }
        } catch (Exception e2) {
            model.addAttribute("exception", e2);
            status = "FAILED";
        }
        Long count = adminService.countAllProjects();
        int countPages = adminService.countPagesofProjects();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
        model.addAttribute("status", status);
        model.addAttribute("admin", admin);
        model.addAttribute("listprojects", listProjects);
        model.addAttribute("countPages", countPages);
        model.addAttribute("countOfprojects", count);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames", allemployees);
        return "projectallocation";
    }

    @GetMapping("/teamallocation")
    public String teamallocation(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        List<TeamListViewDTO> listTeams = null;
        Admin admin = null;
        String status = null;
        try {
            listTeams = adminService.getTeamDetails(pageNo);
        } catch (Exception e1) {
            model.addAttribute("exception", e1);
            status = "FAILED";
        }
        try {
            admin = adminService.fetchAdminData(id);
            if (status == null) {
                status = "SUCCESS";
            }
        } catch (Exception e2) {
            model.addAttribute("exception", e2);
            status = "FAILED";
        }
        Long count = adminService.countAllTeams();
        int countPages = adminService.countPagesofTeams();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
        model.addAttribute("status", status);
        model.addAttribute("admin", admin);
        model.addAttribute("listteams", listTeams);
        model.addAttribute("countPages", countPages);
        model.addAttribute("countOfteams", count);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames", allemployees);
        return "teamallocation";
    }

    @GetMapping("leaveApproval")
    public String leaveApprovaldashboard(HttpServletRequest request, Model model) {
        int pg = Integer.parseInt(request.getParameter("pg"));
        Long addid = AuthenticationSystem.getId();

        String status = null;
        Admin admin;
        List<LeaveApplicationListDTO> leavelist = null;
        try {
            admin = adminService.fetchAdminData(addid);

        } catch (Exception e) {
            admin = new Admin();
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try {
            leavelist = adminService.listAllLeaves(pg);
            if (status == null) {
                status = "SUCCESS";
            }
        } catch (Exception e) {
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        model.addAttribute("status", status);
        model.addAttribute("admin", admin);
        model.addAttribute("pg", pg);
        model.addAttribute("leavelist", leavelist);
        model.addAttribute("totalPages", adminService.totalCountPagesLeaves());
        model.addAttribute("count", adminService.totalLeaves());
        return "leaveApproval";
    }

    @PostMapping("/deleteProject")
    public String deleteProject(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        Long projId = Long.parseLong(request.getParameter("projId"));
        String pg = request.getParameter("pg");
        String status;
        try {
            adminService.deleteProjectById(projId);
            status = "SUCCESS";
        } catch (Exception e) {
            status = "FAILED";
            redirectAttrs.addFlashAttribute("exception", e);
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/projectallocation?pg=" + pg;
    }

    @PostMapping("/deleteTeam")
    public String deleteTeam(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        Long teamId = Long.parseLong(request.getParameter("teamId"));
        String pg = request.getParameter("pg");
        String status;
        try {
            adminService.deleteTeamById(teamId);
            status = "SUCCESS";
        } catch (Exception e) {
            status = "FAILED";
            redirectAttrs.addFlashAttribute("exception", e);
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/teamallocation?pg=" + pg;
    }

    @PostMapping("deactivateUser")
    public String deactivateEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request,
            BindingResult result, RedirectAttributes redirectAttrs) {

        String search = request.getParameter("search");
        String pg = request.getParameter("pg");
        String status;
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.deactivateEmp(employee.getEmpId());
                redirectAttrs.addFlashAttribute("message",
                        "Employee " + employee.getEmpId() + " successfully deactivated");
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return String.format("redirect:/admin/dashboard?search=%s&pg=%s", search, pg);
    }

    @PostMapping("activateUser")
    public String activateEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request,
            BindingResult result, RedirectAttributes redirectAttrs) {
        String search = request.getParameter("search");
        String pg = request.getParameter("pg");
        String status;
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.activateEmp(employee.getEmpId());
                redirectAttrs.addFlashAttribute("message",
                        "Employee " + employee.getEmpId() + " successfully activated");
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return String.format("redirect:/admin/dashboard?search=%s&pg=%s", search, pg);
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("newpass") ChangePasswordDTO changePasswordDTO,
            HttpServletRequest request, RedirectAttributes redirectAttr, BindingResult result) {
        String search = request.getParameter("search");
        String pgNo = request.getParameter("pg");
        String status;
        if (result.hasErrors()) {
            redirectAttr.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.changePassword(changePasswordDTO);
                redirectAttr.addFlashAttribute("message", "Password changed successfully");
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttr.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttr.addFlashAttribute("status", status);
        return String.format("redirect:/admin/dashboard?search=%s&pg=%s", search, pgNo);
    }

    @PostMapping("/leaveAction")
    public String leaveAction(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        Long adminId = AuthenticationSystem.getId();
        Long lid = Long.parseLong(request.getParameter("lid"));
        int pg = Integer.parseInt(request.getParameter("pg"));
        String approve = request.getParameter("approve");
        String status;
        try {
            adminService.leaveAction(lid, adminId, approve);
            status = "SUCCESS";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("exception", e);
            status = "FAILED";
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/leaveApproval?pg=" + pg;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newuser") NewEmployeeDTO newEmployee, BindingResult result,
            RedirectAttributes redirectAttrs) {

        String status;
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            adminService.addEmployee(newEmployee);
            status = "SUCCESS";
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/dashboard?search=null&pg=1";
    }

    @PostMapping("editemployee")
    public String editemployee(@ModelAttribute("employeeinfo") Employee employee, BindingResult result,
            RedirectAttributes redirectAttrs, HttpServletRequest request) {
        String status;
        String search = request.getParameter("search");
        String pg = request.getParameter("pg");
        Long empId = Long.parseLong(request.getParameter("empId"));
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.saveEmployee(empId, employee);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/dashboard?search=" + search + "&pg=" + pg;
    }

    @GetMapping("resignationApproval")
    public String resignationApprovaldashboard(HttpServletRequest request, Model model) {
        int pg = Integer.parseInt(request.getParameter("pg"));

        Long addid = AuthenticationSystem.getId();
        String status = null;
        Admin admin;
        List<ResignationListDTO> resignationList = null;
        try {
            admin = adminService.fetchAdminData(addid);

        } catch (Exception e) {
            admin = new Admin();
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try {
            resignationList = adminService.listAllResignations(pg);
            if (status == null) {
                status = "SUCCESS";
            }
        } catch (Exception e) {
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        model.addAttribute("admin", admin);
        model.addAttribute("pg", pg);
        model.addAttribute("status", status);
        model.addAttribute("resignationList", resignationList);
        model.addAttribute("totalPages", adminService.totalCountPagesResignations());
        model.addAttribute("count", adminService.totalResignations());
        return "resignationApproval";
    }

    @PostMapping("/resignAction")
    public String resignAction(RedirectAttributes redirectAttrs, HttpServletRequest request) {
        Long adminId = AuthenticationSystem.getId();
        Long rid = Long.parseLong(request.getParameter("rid"));
        int pg = Integer.parseInt(request.getParameter("pg"));
        String approve = request.getParameter("approve");
        String status;
        try {
            adminService.resignAction(rid, adminId, approve);
            status = "SUCCESS";
        } catch (Exception e) {
            status = "FAILED";
            redirectAttrs.addFlashAttribute("exception", e);
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/resignationApproval?pg=" + pg;
    }

    @PostMapping("addteam")
    public String addTeam(@ModelAttribute("newteam") NewTeamDTO newTeam, BindingResult result,
            RedirectAttributes redirectAttrs) {

        String status;
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.addTeam(newTeam);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/teamallocation?pg=1";
    }

    @PostMapping("addProj")
    public String addproject(@ModelAttribute("newproj") NewProjectDTO newProjectDTO, BindingResult result,
            RedirectAttributes redirectAttrs) {

        String status;
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        } else {
            try {
                adminService.addProject(newProjectDTO);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/projectallocation?pg=1";
    }

    @PostMapping("addTeamMember")
    public String addTeamMember(@RequestParam("hiddenFieldOfTeams") String teamMemberIds,
            RedirectAttributes redirectAttrs, HttpServletRequest request) {
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        Long tid = Long.parseLong(request.getParameter("teamId"));
        String status;
        if (!teamMemberIds.equals(";")) {
            try{
                adminService.addTeamMembersToTeam(tid, teamMemberIds);
                status = "SUCCESS";
            }
            catch(Exception e){
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        else{
            status = "SUCCESS";
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/admin/editableTeamPage?tid=" + tid + "&pg=" + pageNo;
    }

    @PostMapping("addTeamManager")
    public String addTeamManager(@RequestParam("teamManagerValues") String tm, RedirectAttributes redirectAttrs,
            HttpServletRequest request){
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        Long tid = Long.parseLong(request.getParameter("tid"));
        String status;
        if (!tm.equals("0")) {

            Long teamManagerId = Long.parseLong(tm);
            try {
                adminService.addTeamManagerToTeam(tid, teamManagerId);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        else{
            status = "SUCCESS";
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/admin/editableTeamPage?tid=" + tid + "&pg=" + pageNo;
    }

    @GetMapping("/editableTeamPage")
    public String editableTeamPage(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));

        Long tid = Long.parseLong(request.getParameter("tid"));
        List<EmployeeSelectionView> employeeInTeam = null;
        Admin admin = null;
        String status = null;
        TeamDetailsView teamDetails = null;
        List<EmployeeSelectionView> potentialTM  = null;
        List<EmployeeSelectionView> employees = null;
        try {
            employeeInTeam = adminService.findEmployeesInTeamById(tid);
        } catch (Exception e) {
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try {
            admin = adminService.fetchAdminData(id);
        } catch (Exception e) {
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try{
            teamDetails = adminService.getTeamDetailsById(tid);
            employees = adminService.findEmployeesByDepartment(teamDetails.getDepartment());
            potentialTM = adminService.listAllPotentialTM(teamDetails.getDepartment());
            if (status == null) {
                status = "SUCCESS";
            }
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        model.addAttribute("admin", admin);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("employeeInTeam", employeeInTeam);
        model.addAttribute("teamdetails", teamDetails);
        model.addAttribute("employees", employees);
        model.addAttribute("potentialTM", potentialTM);
        model.addAttribute("status", status);
        return "editableTeam";
    }

    @GetMapping("/editableProjectPage")
    public String editableProjectPage(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        Long projid = Long.parseLong(request.getParameter("projid"));
        List<TeamSelectionViewDTO> teamInProject = null;
        ProjectDetailsView projectDetails = null;
        String status = null;
        Admin admin = null;
        try{
            admin = adminService.fetchAdminData(id);
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try{
            teamInProject = adminService.findTeamsInProjectById(projid);
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        try{
            projectDetails = adminService.getProjectDetailsById(projid);
            if(status == null){
                status = "SUCCESS";
            }
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        

        List<EmployeeSelectionView> potentialPm = adminService.listAllPotentialPM();
        List<TeamSelectionView> teams = adminService.getAllTeams();
        model.addAttribute("admin", admin);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("projectdetails", projectDetails);
        model.addAttribute("potentialpm", potentialPm);
        model.addAttribute("teamsInProject", teamInProject);
        model.addAttribute("teams", teams);
        model.addAttribute("status", status);

        return "editableproject";
    }
    @PostMapping("addTeamToProject")
    public String addTeamToProject(@RequestParam("hiddenFieldOfTeams") String teamMemberIds,
            RedirectAttributes redirectAttrs, HttpServletRequest request){
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        Long projid = Long.parseLong(request.getParameter("projId"));
        String status;
        try {
            adminService.addTeamsToProject(projid, teamMemberIds);
            status = "SUCCESS";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("exception",e);
            status = "FAILED";
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/admin/editableProjectPage?projid=" + projid + "&pg=" + pageNo;
    }

    @PostMapping("addProjectManager")
    public String addProjectManager(@ModelAttribute("teamManagerValues") ProjectPmDTO projectPm, BindingResult result,
            RedirectAttributes redirectAttrs, HttpServletRequest request) {

        int pg = Integer.parseInt(request.getParameter("pg"));
        Long projid = Long.parseLong(request.getParameter("projId"));
        String status;

        if (result.hasErrors()) {
            status = "FAILED";
            redirectAttrs.addFlashAttribute("error", result);
        } else {
            try {
                redirectAttrs.addFlashAttribute("test", projectPm);
                adminService.addProjectManagerToProject(projid, projectPm);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }

        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/admin/editableProjectPage?projid=" + projid + "&pg=" + pg;
    }
    // @GetMapping("/addemployee")
    // public String addemployee(HttpServletRequest request, Model model) {

    // return "addemployee";
    // }
    // @GetMapping("/addproject")
    // public String addproject() {
    // return "addproject";
    // }
    // @GetMapping("/allocateproject")
    // public String allocateproject() {
    // return "allocateproject";
    // }
    // @GetMapping("/addteam")
    // public String addteam() {
    // return "redirect:admin/teamallocation";
    // }
    // @GetMapping("/allocateteam")
    // public String allocateteam() {
    // return "allocateteam";
    // }
    // @GetMapping("/adddepartment")
    // public String adddepartment() {
    // return "adddepartment";
    // }
    // @GetMapping("/allocatedepartment")
    //
    // public String allocatedepartment(HttpServletRequest request, Model model) {
    // return "allocatedepartment";
    //
    // }

    // @GetMapping("/departmentallocation")
    // public String departmentallocation(HttpServletRequest request, Model model)
    // throws UserNotFoundException{
    // Long id = AuthenticationSystem.getId();
    // int pageNo = Integer.parseInt(request.getParameter("pg"));

    // model.addAttribute("pageNo", pageNo);
    // model.addAttribute("allemployeenames",allemployees);
    // return "departmentallocation";
    // }

    // departmentName, hod
    // @PostMapping("/addDept")
    // public String addDepartment(@ModelAttribute("newdept") NewDepartmentDTO
    // newDepartment, BindingResult result, RedirectAttributes redirectAttrs )
    // throws DepartmentNotFoundException, EmployeeNotFoundException{
    // // return ResponseEntity.ok().body();
    // System.out.println(newDepartment);
    // adminService.addDepartment(newDepartment);
    // if (result.hasErrors()){
    // redirectAttrs.addFlashAttribute("result", result);
    // }
    // else{
    // redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new
    // SuccessDetails(
    // new Date(),
    // "Created",
    // "New department has been created"
    // )));
    // }
    // return "redirect:/admin/departmentallocation?pg=1";
    // }

    // resignation approval dashboard

    // @PostMapping("editDepartment")
    // public String editDepartment(@ModelAttribute("modifydept") EditDepartmentDTO
    // editDepartment, BindingResult result, RedirectAttributes redirectAttrs,
    // HttpServletRequest request) throws Exception{
    //// String pg = request.getParameter("pg");
    // Long deptId = Long.parseLong(request.getParameter("deptId"));
    //
    //// System.out.println("\n\n\n\n\n\n");
    //// System.out.println(editDepartment);
    //
    // adminService.editDepartment(deptId, editDepartment);
    //
    // if (result.hasErrors()){
    // redirectAttrs.addFlashAttribute("result", result);
    // }
    // else{
    // redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new
    // SuccessDetails(
    // new Date(),
    // "Updated",
    // "Department is updated"
    // )));
    // }
    // return "redirect:/admin/departmentallocation?pg="+1;
    // }

    // @PostMapping("editTeam")
    // public String editTeam(@ModelAttribute("modifyteam") EditTeamDTO editTeam,
    // BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest
    // request) throws Exception{
    // String pg = request.getParameter("pg");
    // Long teamId = Long.parseLong(request.getParameter("teamId"));

    // adminService.editTeam(teamId, editTeam);

    // if (result.hasErrors()){
    // redirectAttrs.addFlashAttribute("result", result);
    // }
    // else{
    // redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new
    // SuccessDetails(
    // new Date(),
    // "Updated",
    // "Team is updated"
    // )));
    // }
    // return "redirect:/admin/teamallocation?pg="+pg;
    // }

    /*
     * @PostMapping("editProj") public String editProj(@ModelAttribute("modifyproj")
     * EditProjectDTO editProjectDTO, BindingResult result, RedirectAttributes
     * redirectAttrs, HttpServletRequest request) throws Exception{ // String pg =
     * request.getParameter("pg"); Long projectId =
     * Long.parseLong(request.getParameter("projectId"));
     * 
     * // System.out.println("\n\n\n\n\n\n"); // System.out.println(editDepartment);
     * 
     * adminService.editProject(projectId, editProjectDTO);
     * 
     * if (result.hasErrors()){ redirectAttrs.addFlashAttribute("result", result); }
     * else{ redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new
     * SuccessDetails( new Date(), "Updated", "Project is updated" ))); } return
     * "redirect:/admin/projectallocation?pg="+1; }
     */

}
