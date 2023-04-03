package org.antwalk.ems.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.ems.dto.ResignationDTO;
import org.antwalk.ems.exception.AdminNotFoundException;
import org.antwalk.ems.exception.EmployeeNotFoundException;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.ProfDetails;
import org.antwalk.ems.model.QualificationDetails;
import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.FamilyDetailsRepository;
import org.antwalk.ems.security.AuthenticationSystem;
import org.antwalk.ems.service.AdminService;
import org.antwalk.ems.service.EmployeeService;
import org.antwalk.ems.view.LeaveApplicationListView;
import org.antwalk.ems.view.LeaveLeftView;
import org.antwalk.ems.view.ResignationView;
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
    public String employeeDashboard(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();

        Employee employee;
        String status;

		try {
			employee = employeeService.findEmployee(id);
            status = "SUCCESS";
		} catch (Exception e) {
	        employee=new Employee();
			model.addAttribute("exception", e);
            status = "FAILED";
		}
        model.addAttribute("status", status);
        model.addAttribute("employee", employee);
        return "myProfile";
    }

    @GetMapping("employeepersonaldetails")
    public String employeePersonalDetails(HttpServletRequest request, Model model){
        Long id = AuthenticationSystem.getId();
        EmployeeDetails employeeDetails;
        String status;

		try {
			employeeDetails = employeeService.employeeInfo(id);
            status = "SUCCESS";
		} catch (Exception e) {

	        employeeDetails = new EmployeeDetails();
			model.addAttribute("exception", e);
            status = "FAILED";
		}
        model.addAttribute("status", status);
        model.addAttribute("employeeinfomation", employeeDetails);
        
        return "personalDetails";
    }

    @GetMapping("leaveApplication")
       public String leaveApplicationView(HttpServletRequest request, Model model) throws EmployeeNotFoundException {
       Long id = AuthenticationSystem.getId();
       
        int pg = Integer.parseInt(request.getParameter("pg"));
       
        List<LeaveApplicationListView> leaveapplications = null;
        int totalCount = 0;
        int totalPages = 0;
        List<Integer> applied=new ArrayList<>();
        String status;
        LeaveLeftView leaves;
		try {
			leaveapplications = employeeService.findEmployeeLeaves(id,pg);
			totalCount = employeeService.totalLeaves(id);
			totalPages = employeeService.totalCountOfPages(id);
			applied=employeeService.countApplied(id);
			status = "SUCCESS";
			
		} catch (Exception e) {
			model.addAttribute("exception",e);
			System.out.println(e);
			status = "FAILED";
		}
		try {
			leaves = employeeService.findEmployeeLeavesLeft(id);
			status = "SUCCESS";
		}
		catch(Exception e) {
			leaves = null;
			model.addAttribute("exception",e);
			status = "FAILED";
		}
        model.addAttribute("employee",leaves);
        model.addAttribute("appliedLeaves",applied);
		model.addAttribute("status",status);
        model.addAttribute("leavelist",leaveapplications);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("count",totalCount);
        model.addAttribute("pg", pg);
       return "applyLeave";
    }

    @GetMapping("resign")
    public String resign(HttpServletRequest request, Model model) throws EmployeeNotFoundException, AdminNotFoundException{
        Long id = AuthenticationSystem.getId();
        ResignationDTO resign;
        String status;
        try{
            
            resign = employeeService.resign(id);
            status = "SUCCESS";
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            resign = null;
            status = "FAILED";
        }
        model.addAttribute("status",status);
        model.addAttribute("resign", resign);
        return "applyResignation";
    }

    @GetMapping("qualificationdetails")
    public String qualificationdetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();
        List<QualificationDetails> listOfQualificationDetails = null;
        String status;

        try{
            listOfQualificationDetails = employeeService.findQualificationDetails(id);
            status = "SUCCESS";
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        model.addAttribute("status", status);
        model.addAttribute("listOfQualification", listOfQualificationDetails);
        return "myQualification";
    }

    @GetMapping("professionaldetails")
    public String professionaldetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();

        List<ProfDetails> listOfProfDetails = null;
        String status;
        try{
            listOfProfDetails = employeeService.findProfessionalDetails(id);
            status = "SUCCESS";
        }
        catch(Exception e){
            model.addAttribute("exception", e);
            status = "FAILED";
        }
        model.addAttribute("status", status);
        model.addAttribute("listOfProfession", listOfProfDetails);
        return "myProfession";
    }

    @GetMapping("familyDetails")
    public String familydetails(HttpServletRequest request, Model model) {
        Long id = AuthenticationSystem.getId();

        List<FamilyDetails> listOfFamilyDetails = null;
        String status;
        try{
            listOfFamilyDetails = employeeService.findFamilyDetails(id);
            status = "SUCCESS";
        }
        catch(Exception e){
            status = "FAILED";
            model.addAttribute("exception", e);
        }
        model.addAttribute("status", status);
        model.addAttribute("listOfFamilyDetails", listOfFamilyDetails);
        return "myFamily";
    }

    @PostMapping("/applyLeave")
    public String applyLeave(@ModelAttribute("leave") LeaveApplication leaveApplication, BindingResult result, RedirectAttributes redirectAttrs){
        
        Long id = AuthenticationSystem.getId();
        String status;
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("error",result);
            status = "FAILED";
        }
        else{
            try {
                employeeService.applyLeave(id,leaveApplication);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status", status);
        return "redirect:/employee/leaveApplication?pg=1";
    }

    @PostMapping("/deletefamilymember")
    public String deletefamilymember( RedirectAttributes redirectAttrs, HttpServletRequest request  ){
    	Long fid=Long.parseLong(request.getParameter("fid"));
        Long empId = AuthenticationSystem.getId();
        String status;

        try{
            employeeService.deleteFamilyMemberById(empId,fid);
            status = "SUCCESS";
        }
        catch(Exception e){
            redirectAttrs.addFlashAttribute("exception",e);
            status = "FAILED";
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/familyDetails";
    }

    @PostMapping("/deleteQualification")
    public String deleteQualification( RedirectAttributes redirectAttrs, HttpServletRequest request){
    	
        Long qid=Long.parseLong(request.getParameter("qid"));
        Long empId = AuthenticationSystem.getId();
        String status;

        try{
            employeeService.deleteQualificationById(empId,qid);
            status = "SUCCESS";
        }
        catch(Exception e){
            redirectAttrs.addFlashAttribute("exception",e);
            status = "FAILED";
        }
        redirectAttrs.addFlashAttribute("status",status);

        return "redirect:/employee/qualificationdetails";
    }

    @PostMapping("/deleteProfession")
    public String deleteProfession( RedirectAttributes redirectAttrs, HttpServletRequest request){
        
        Long pid=Long.parseLong(request.getParameter("pid"));
        Long empId = AuthenticationSystem.getId();
        String status;

        try{
            employeeService.deleteProfessionById(empId,pid);
            status = "SUCCESS";
        }
        catch(Exception e){
            redirectAttrs.addFlashAttribute("exception",e);
            status = "FAILED";
        }
        redirectAttrs.addFlashAttribute("status",status);

        return "redirect:/employee/professionaldetails";
    }

    @PostMapping("personaldetailsofemployee")
    public String editemployee(@ModelAttribute("emppersonaldetails") EmployeeDetails employeeDetails,
            BindingResult result, RedirectAttributes redirectAttrs) {

        Long id = AuthenticationSystem.getId();
        String status;

        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("error",result);
            status = "FAILED";
        }
        else{
            try{
                employeeService.saveEmployeeDetails(id,employeeDetails);
                status = "SUCCESS";
            }
            catch(Exception e){
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/employeepersonaldetails";
    }

    @PostMapping("applyResignation")
    public String applyResignation(@ModelAttribute("resignform") Resignation resignation, BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request){
        Long id = AuthenticationSystem.getId();
        String status;
        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("error", result);
            status = "FAILED";
        }
        else{
            try{
                employeeService.applyForResignation(id, resignation);
                status = "SUCCESS";
            }
            catch(Exception e){
                status = "FAILED";
                redirectAttrs.addFlashAttribute("exception",e);
            }
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/resign";
    }

    @PostMapping("addQualification")
    public String addQualification(@ModelAttribute("qualification") QualificationDetails qualificationDetails, BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request){
        Long id = AuthenticationSystem.getId();
        String status;
        
        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("error",result);
            status = "FAILED";
        }
        else{
            try {
                employeeService.addQualification(id, qualificationDetails);
                status = "SUCCESS";
            } catch (Exception e) {
                redirectAttrs.addFlashAttribute("exception", e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/qualificationdetails";
    }

    @PostMapping("addFamily")
    public String addFamily(@ModelAttribute("family") FamilyDetails familyDetails, BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request){
        Long id = AuthenticationSystem.getId();
        String status;

        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("error",result);
            status = "FAILED";
        }
        else{
            try{
                employeeService.addFamily(id, familyDetails);
                status = "SUCCESS";
            }
            catch(Exception e){
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/familyDetails";
    }

    @PostMapping("addProfession")
    public String addProfession(@ModelAttribute("profession") ProfDetails profDetails, BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) throws EmployeeNotFoundException{
        Long id = AuthenticationSystem.getId();
        String status;

        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("error",result);
            status = "FAILED";
        }
        else{
            try{
                employeeService.addProfession(id, profDetails);
                status = "SUCCESS";
            }
            catch(Exception e){
                redirectAttrs.addFlashAttribute("exception",e);
                status = "FAILED";
            }
        }
        redirectAttrs.addFlashAttribute("status",status);
        return "redirect:/employee/professionaldetails";
    }

    // @GetMapping("editemployeedetails")
    // public String editemployeedetails(HttpServletRequest request, Model model) {
    //     Long id = AuthenticationSystem.getId();
    //     Employee employee;
	// 	try {
	// 		employee = employeeService.findEmployee(id);
	// 	} catch (EmployeeNotFoundException e) {
	// 		// TODO Auto-generated catch block
	//         employee=new Employee();
	// 		e.printStackTrace();
	// 	}
    //     model.addAttribute("employee", employee);
    //     return "myProfile";
    // }

    // @GetMapping("personaldetails")
    // public String personaldetails(HttpServletRequest request, Model model) {
    //     Long id = AuthenticationSystem.getId();
    //     EmployeeDetails employeeDetails;
    //     String status;

	// 	try {
	// 		employeeDetails = employeeService.employeeInfo(id);
    //         status = "SUCCESS";
	// 	} catch (Exception e) {

	//         employeeDetails = new EmployeeDetails();
	// 		model.addAttribute("exception", e);
    //         status = "FAILED";
	// 	}
    //     model.addAttribute("status", status);
    //     model.addAttribute("employeeinfo", employeeDetails);
    //     return "myProfile";
    // }

    // @GetMapping("dashboards")
    // public String employeesdashboard(HttpServletRequest request, Model model) {
    //     Long id = AuthenticationSystem.getId();
    //     System.out.println(id);
    //     Employee employee = employeeRepository.getById(id);
    //     model.addAttribute("employee", employee);
    //     return "personalDetails";
    // }


    // @GetMapping("adminUserView")
    // public String adminUserView(HttpServletRequest request, Model model) {
    //     Long id = AuthenticationSystem.getId();
    //     System.out.println(id);
    //     List<FamilyDetails> listOfFamilyDetails =
    //     employeeService.listAllFamilyDetails(id);
    //     model.addAttribute("listOfFamily",listOfFamilyDetails);
    //     model.addAttribute("familyDetails", new ArrayList<FamilyDetails>());
    //     return "editUser";
    // }

    

    // @PostMapping("postfamilydetails")
    // public String postfamilydetails(@ModelAttribute("listOfFamily") List<FamilyDetails> families,
    //         BindingResult result) {
    //     familyDetailsRepository.saveAll(families); // save all updated users to the
    //     database
    //     if(result.hasErrors()){
    //     return "error";
    //     }
    //     model.addAttribute("family", families);
    //     return "redirect:/familyDetails";
    // }
}
