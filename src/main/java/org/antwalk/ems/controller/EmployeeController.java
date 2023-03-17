package org.antwalk.ems.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
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
    // return ResponseEntity.ok().body(userService.createUser(user));
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
    public String employeedashboard(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        Employee employee = employeeService.findEmployee(id);
        model.addAttribute("employee", employee);
        return "myProfile";
    }

    @GetMapping("editemployeedetails")
    public String editemployeedetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        Employee employee = employeeService.findEmployee(id);
        model.addAttribute("employee", employee);
        return "myProfile";
    }

    @GetMapping("personaldetails")
    public String personaldetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        EmployeeDetails employeeDetails = employeeService.employeeInfo(id);
        model.addAttribute("employeeinfo", employeeDetails);
        return "myProfile";
    }

    @GetMapping("professionaldetails")
    public String professionaldetails() {
        return "myProfession";
    }

    @GetMapping("qualificationdetails")
    public String qualificationdetails() {
        return "myQualification";
    }

    @PostMapping("/applyLeave")
    public String editemployee(@ModelAttribute("leave") LeaveApplication leaveApplication, BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException{
           
        Long id = AuthenticationSystem.getId();
        employeeService.applyLeave(id,leaveApplication);
        System.out.println(leaveApplication);

        // if (result.hasErrors()){
        //     redirectAttrs.addFlashAttribute("result", result);
        // }
        // else{
        //     redirectAttrs.addFlashAttribute("result",ResponseEntity.ok().body(new SuccessDetails(
        //         new Date(),
        //         "Added",
        //         "New leave is added"
        //     )));
        // }
        return "redirect:/leaveApplication?pg=1";
    }

    @GetMapping("leaveApplication")
       public String leaveApplicationView(HttpServletRequest request, Model model) {
       Long id = AuthenticationSystem.getId();
       
    //    int pg = Integer.parseInt(request.getParameter("pg"));
    int pg=1;
       
        List<LeaveApplication> leaveapplications = employeeService.findEmployeeLeaves(id,pg);
        model.addAttribute("leavelist",leaveapplications);
        model.addAttribute("pg", pg);
       return "applyLeave";
       }

    @GetMapping("dashboards")
    public String employeesdashboard(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee", employee);
        return "personalDetails";
    }

    @GetMapping("familyDetails")
    public String familyDetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        List<FamilyDetails> listOfFamilyDetails = employeeService.listAllFamilyDetails(id);
        model.addAttribute("listOfFamily", listOfFamilyDetails);
        return "myFamily";
    }

    @GetMapping("adminUserView")
    public String adminUserView(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println(id);
        // List<FamilyDetails> listOfFamilyDetails =
        // employeeService.listAllFamilyDetails(id);
        // model.addAttribute("listOfFamily",listOfFamilyDetails);
        // model.addAttribute("familyDetails", new ArrayList<FamilyDetails>());
        return "editUser";
    }

    @GetMapping("employeepersonaldetails")
    public String employeepersonaldetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        System.out.println("Emp details  " + id);
        EmployeeDetails employeeDetails = employeeService.employeeInfo(id);
        model.addAttribute("employeeinfomation", employeeDetails);
        System.out.println(employeeDetails);
        // model.addAttribute("familyDetails", new ArrayList<FamilyDetails>());
        return "personalDetails";
    }

    @PostMapping("postfamilydetails")
    public String postfamilydetails(@ModelAttribute("listOfFamily") List<FamilyDetails> families,
            BindingResult result) {
        // familyDetailsRepository.saveAll(families); // save all updated users to the
        // database
        // if(result.hasErrors()){
        // return "error";
        // }
        // model.addAttribute("family", families);
        return "redirect:/familyDetails";
    }

    @PostMapping("personaldetailsofemployee")
    public String editemployee(@ModelAttribute("emppersonaldetails") EmployeeDetails employeeDetails,
            BindingResult result, RedirectAttributes redirectAttrs) throws UserNotFoundException {
        // familyDetailsRepository.saveAll(families); // save all updated users to the
        // database

        Long id = AuthenticationSystem.getId();
        System.out.println("hello " + id);
        System.out.println(employeeDetails);
        employeeDetailsRepository.save(employeeDetails);
        return "redirect:/employee/employeepersonaldetails";
    }
}
