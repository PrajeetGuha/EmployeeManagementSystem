package org.antwalk.ems.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.pojo.SuccessDetails;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.antwalk.ems.service.AdminService;
import org.antwalk.ems.view.EmployeeListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<String> allemployees = adminService.listAllEmployees();
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
    	Admin admin = adminService.fetchAdminData(id);
    	model.addAttribute("admin",admin);
		return "projectallocation";
	}
    @GetMapping("/teamallocation")
   	public String teamallocation(HttpServletRequest request, Model model) throws UserNotFoundException{
    	Long id = AuthenticationSystem.getId();
    	Admin admin = adminService.fetchAdminData(id);
    	 model.addAttribute("admin",admin);
   		return "teamallocation";
   	}
    @GetMapping("/departmentallocation")
   	public String departmentallocation(HttpServletRequest request, Model model) throws UserNotFoundException{
    	Long id = AuthenticationSystem.getId();
    	Admin admin = adminService.fetchAdminData(id);
    	 model.addAttribute("admin",admin);
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
    public String addUser(@ModelAttribute("newuser") NewEmployeeDTO newEmployee, BindingResult result, RedirectAttributes redirectAttrs ){
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
}
