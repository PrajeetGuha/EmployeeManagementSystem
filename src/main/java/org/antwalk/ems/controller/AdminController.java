package org.antwalk.ems.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antwalk.ems.dto.NewDepartmentDTO;
import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.exception.DepartmentNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Department;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.pojo.SuccessDetails;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.antwalk.ems.service.AdminService;
import org.antwalk.ems.service.ReportService;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.EmployeeSelectionView;
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

    @GetMapping("dashboard")
    public String admindashboard(HttpServletRequest request, Model model) throws UserNotFoundException{
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        String search = request.getParameter("search");
        Admin admin = adminService.fetchAdminData(id);
        int pageCount = adminService.countPagesOfEmployees(search);
        Long empCount = adminService.countEmployees(search);
        List<String> usernames = adminService.listAllUsernames();
        List<String> emailIds = adminService.listAllEmails();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
        List<EmployeeListView> employeeListViews = adminService.listEmployees(pageNo,search);
        List<String> listOfdepartments = adminService.listDepartments();
        model.addAttribute("admin",admin);
        model.addAttribute("employees", employeeListViews);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("empCount",empCount);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("allemployeenames",allemployees);
        model.addAttribute("usernames",usernames);
        model.addAttribute("emailIds", emailIds);
        model.addAttribute("departments", listOfdepartments);
        model.addAttribute("search", search);

        //System.out.println(employeeListViews);
        return "admindashboard";
    }
    @GetMapping("/addemployee")
   	public String addemployee(HttpServletRequest request, Model model) {
        

        return "addemployee";
   	}
    @GetMapping("/addproject")
   	public String addproject() {
          		return "addproject";
   	}
    @GetMapping("/allocateproject")
   	public String allocateproject() {
          		return "allocateproject";
   	}
    @GetMapping("/addteam")
   	public String addteam() {
          		return "addteam";
   	}
    @GetMapping("/allocateteam")
   	public String allocateteam() {
          		return "allocateteam";
   	}
    @GetMapping("/adddepartment")
   	public String adddepartment() {
          		return "adddepartment";
   	}
    @GetMapping("/allocatedepartment")

   	public String allocatedepartment(HttpServletRequest request, Model model) {
          		return "allocatedepartment";

   	}
    @GetMapping("/projectallocation")
	public String projectallocation(HttpServletRequest request, Model model) throws UserNotFoundException{
    	Long id = AuthenticationSystem.getId();
    	int pageNo = Integer.parseInt(request.getParameter("pg"));
        List<Project> listProjects = adminService.getAllProjects(pageNo);
    	Admin admin = adminService.fetchAdminData(id);
        Long count = adminService.countAllProjects();
        int countPages = adminService.countPagesofProjects();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
    	model.addAttribute("admin",admin);
        model.addAttribute("listprojects", listProjects);
        model.addAttribute("countPages", countPages);
        model.addAttribute("countOfprojects", count);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames",allemployees);
		return "projectallocation";
	}
    @GetMapping("/teamallocation")
   	public String teamallocation(HttpServletRequest request, Model model) throws UserNotFoundException{
    	Long id = AuthenticationSystem.getId();
    	int pageNo = Integer.parseInt(request.getParameter("pg"));
        List<Team> listTeams = adminService.getAllTeams(pageNo);
    	Admin admin = adminService.fetchAdminData(id);
        Long count = adminService.countAllTeams();
        int countPages = adminService.countPagesofTeams();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
    	model.addAttribute("admin",admin);
        model.addAttribute("listteams", listTeams);
        model.addAttribute("countPages", countPages);
        model.addAttribute("countOfteams", count);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames",allemployees);
   		return "teamallocation";
   	}
    @GetMapping("/departmentallocation")
   	public String departmentallocation(HttpServletRequest request, Model model) throws UserNotFoundException{
    	Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        List<Department> listDepartments = adminService.getAllDepartments(pageNo);
    	Admin admin = adminService.fetchAdminData(id);
        Long count = adminService.countAllDepartments();
        int countPages = adminService.countPagesOfDepartments();
        List<EmployeeSelectionView> allemployees = adminService.listAllEmployees();
    	model.addAttribute("admin",admin);
        model.addAttribute("listdepartments", listDepartments);
        model.addAttribute("countPages", countPages);
        model.addAttribute("countOfDepartments", count);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("allemployeenames",allemployees);
   		return "departmentallocation";
   	}

    @PostMapping("deactivateUser")
    public String deactivateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam int pgNo, BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException{
        adminService.deactivateEmp(employee.getEmpId());
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("result", result);
        }
        else{
            redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new SuccessDetails(
                new Date(),
                "Deactivated",
                "The employee " + employee.getEmpId() + " has been deactivated"
            )));
        }
        return "redirect:/admin/dashboard?search=null&pg="+pgNo;
    }

    @PostMapping("activateUser")
    public String activateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam int pgNo, BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException{
        adminService.activateEmp(employee.getEmpId());
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("result", result);
        }
        else{
            redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new SuccessDetails(
                new Date(),
                "Deactivated",
                "The employee " + employee.getEmpId() + " has been activated"
            )));
        }
        return "redirect:/admin/dashboard?search=null&pg="+pgNo;
    }


    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newuser") NewEmployeeDTO newEmployee, BindingResult result, RedirectAttributes redirectAttrs ) throws DepartmentNotFoundException{
        // return ResponseEntity.ok().body();
        adminService.addEmployee(newEmployee);
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("result", result);
        }
        else{
            redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new SuccessDetails(
                new Date(),
                "Created",
                "New user has been created"
            )));
        }
        return "redirect:/admin/dashboard?search=null&pg=1";
    }

    // departmentName, hod
    @PostMapping("/addDept")
    public String addDepartment(@ModelAttribute("newdept") NewDepartmentDTO newDepartment, BindingResult result, RedirectAttributes redirectAttrs ) throws DepartmentNotFoundException{
        // return ResponseEntity.ok().body();
        System.out.println(newDepartment);
        adminService.addDepartment(newDepartment);
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("result", result);
        }
        else{
            redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new SuccessDetails(
                new Date(),
                "Created",
                "New department has been created"
            )));
        }
        return "redirect:/admin/departmentallocation?pg=1";
    }


    @GetMapping("/report")
    public String generateEmployeeReport(HttpServletResponse response, HttpServletRequest request) throws IOException{
        Long empId = Long.parseLong(request.getParameter("empId"));
        String pageNo = request.getParameter("pg");
        String search = request.getParameter("search");
        reportService.generateEmployeeReport(response, empId);
        return "redirect:/admin/dashboard?search="+ search + "&pg="+ pageNo;
    }

    @GetMapping("editemployeedetails")
    public String editemployeedetails(HttpServletRequest request, Model model){
        String id = request.getParameter("empId");
        String search =request.getParameter("search");
        String pg=request.getParameter("pg");
        Long id_val=Long.parseLong(id);
        Employee employee = employeeRepository.getById(id_val);
        model.addAttribute("employee",employee);
        model.addAttribute("search",search);
        model.addAttribute("pg",pg);
        return "editUser";
    }

    // @GetMapping("editempdata")
    // public String editempdata(HttpServletRequest request, Model model){
    //     model
    //     return "editUser";
    // }
    @PostMapping("editemployee")
    public String editemployee(@ModelAttribute("employeeinfo") Employee employee, BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException{
            employeeRepository.save(employee);
            // ?search="+search+"&pg="+pg
            return "redirect:/admin/dashboard?search=null&pg=1";
    }

}
