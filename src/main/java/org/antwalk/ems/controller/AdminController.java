package org.antwalk.ems.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @RestController
// @RequestMapping("/dashboard/admin")
@Controller
@RequestMapping("admin")
public class AdminController {

    // @Autowired
    // UserService userService;
    
    // @PostMapping("/addUser")
    // @PreAuthorize(value = "hasRole('ADMIN')")
    // public ResponseEntity<User> addUser(@RequestBody User user){
    //     return ResponseEntity.ok().body(userService.createUser(user));
    // }

    @Autowired
    AdminService adminService;

    @GetMapping("dashboard")
    public String admindashboard(HttpServletRequest request, Model model) throws UserNotFoundException{
        Long id = AuthenticationSystem.getId();
        int pageNo = Integer.parseInt(request.getParameter("pg"));
        Admin admin = adminService.fetchAdminData(id);
        int pageCount = adminService.countPagesOfEmployees();
        Long empCount = adminService.countEmployees();
        List<EmployeeListView> employeeListViews = adminService.listEmployees(pageNo);
        model.addAttribute("admin",admin);
        model.addAttribute("employees", employeeListViews);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("empCount",empCount);
        model.addAttribute("pageNo",pageNo);
        //System.out.println(employeeListViews);
        return "admindashboard";
    }
    @GetMapping("/addemployee")
   	public String addemployee() {
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
   	public String allocatedepartment() {
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
    public String deactivateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam int pgNo) throws UserNotFoundException{
        adminService.deactivateEmp(employee.getEmpId());
        // return ResponseEntity.ok().body(new SuccessDetails(
        //     new Date(),
        //     "Deactivated",
        //     "The employee " + employee.getEmpId() + " has been deactivated"
        // ));
        return "redirect:/admin/dashboard?pg="+pgNo;
    }

    @PostMapping("activateUser")
    public String activateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam int pgNo) throws UserNotFoundException{
        adminService.activateEmp(employee.getEmpId());
        // return ResponseEntity.ok().body(new SuccessDetails(
        //     new Date(),
        //     "Activated",
        //     "The employee " + employee.getEmpId() + " has been activated"
        // ));
        return "redirect:/admin/dashboard?pg="+pgNo;
    }
}
