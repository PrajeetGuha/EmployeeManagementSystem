package org.antwalk.ems.controller;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
// @RequestMapping("/dashboard/admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Controller
@RequestMapping("employee")
public class EmployeeController {

    // @Autowired
    // UserService userService;
    
    // @PostMapping("/addUser")
    // @PreAuthorize(value = "hasRole('ADMIN')")
    // public ResponseEntity<User> addUser(@RequestBody User user){
    //     return ResponseEntity.ok().body(userService.createUser(user));
    // }

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("dashboard")
    public String dashboard(HttpServletRequest request, Model model){
        Long id = AuthenticationSystem.getId();
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee",employee);
        return "employeedashboard";
    }
}
