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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("deactivateUser")
    public ResponseEntity<SuccessDetails> deactivateEmployee(@RequestBody Employee employee) throws UserNotFoundException{
        adminService.deactivateEmp(employee.getEmpId());
        return ResponseEntity.ok().body(new SuccessDetails(
            new Date(),
            "Deactivated",
            "The employee " + employee.getEmpId() + " has been deactivated"
        ));
    }

    @PostMapping("activateUser")
    public ResponseEntity<SuccessDetails> activateEmployee(@RequestBody Employee employee) throws UserNotFoundException{
        adminService.deactivateEmp(employee.getEmpId());
        return ResponseEntity.ok().body(new SuccessDetails(
            new Date(),
            "Activated",
            "The employee " + employee.getEmpId() + " has been activated"
        ));
    }
    
    
}
