package org.antwalk.ems.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.antwalk.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @RestController
// @RequestMapping("/dashboard/admin")
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
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FamilyDetailsRepository familyDetailsRepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @GetMapping("dashboard")
    public String employeedashboard(HttpServletRequest request, Model model){
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee",employee);
        return "myProfile";
    }

    @GetMapping("editemployeedetails")
    public String editemployeedetails(HttpServletRequest request, Model model){
        String id = request.getParameter("empId");
        Long id=Long
        System.out.println(id);
        Employee employee = employeeRepository.getById((id));
        model.addAttribute("employee",employee);
        return "myProfile";
    }

    @GetMapping("personaldetails")
    public String personaldetails(HttpServletRequest request, Model model){
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        EmployeeDetails employeeDetails = employeeDetailsRepository.(id);
        model.addAttribute("employeeinfo",employeeDetails);
        return "myProfile";
    }

    @GetMapping("dashboards")
    public String employeesdashboard(HttpServletRequest request, Model model){
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee",employee);
        return "personalDetails";
    }
    
    @GetMapping("familyDetails")
    public String familyDetails(HttpServletRequest request, Model model) {
    	Long id = AuthenticationSystem.getId();
        System.out.println(id);
    	List<FamilyDetails> listOfFamilyDetails = employeeService.listAllFamilyDetails(id);
    	model.addAttribute("listOfFamily",listOfFamilyDetails);
    	return "myFamily";
    }



    
    @GetMapping("adminUserView")
    public String adminUserView(HttpServletRequest request, Model model) {
    	Long id = AuthenticationSystem.getId();
        System.out.println(id);
    	// List<FamilyDetails> listOfFamilyDetails = employeeService.listAllFamilyDetails(id);
    	// model.addAttribute("listOfFamily",listOfFamilyDetails);
        // model.addAttribute("familyDetails", new ArrayList<FamilyDetails>());
    	return "editUser";
    }


    @PostMapping("postfamilydetails")
    public String postfamilydetails(@ModelAttribute("listOfFamily") List<FamilyDetails> families,BindingResult result) {
        System.out.println("Hi Done");
            // familyDetailsRepository.saveAll(families); // save all updated users to the database
            // if(result.hasErrors()){
            //     return "error";
            // }
            // model.addAttribute("family", families);
            return "redirect:/familyDetails";
    }
    
    @PostMapping("editemployee")
    public String editemployee(@ModelAttribute("employeeinfo") Employee employee, BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException{
            // familyDetailsRepository.saveAll(families); // save all updated users to the database
            System.out.println("Started");
            System.out.println("Id of employee "+employee.getEmpId());
            System.out.println(employee);
            Long id = AuthenticationSystem.getId();
            // System.out.println(employees);
            employeeRepository.save(employee);
            System.out.println(employee.getEmpId());
            System.out.println(employee.getEmpName());
            System.out.println(employee.getBranch());
            System.out.println("Done");
            return "redirect:/employee/dashboard";
    }
}
