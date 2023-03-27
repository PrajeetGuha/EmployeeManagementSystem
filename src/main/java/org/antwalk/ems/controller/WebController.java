package org.antwalk.ems.controller;

import org.antwalk.ems.security.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebController {

    @GetMapping("/")
    public String index(){
        return "redirect:/welcome";
    }
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	 @GetMapping("/login")
	 public String login(){
		if (AuthenticationSystem.isLoggedAs("ROLE_ANONYMOUS"))
	 		return "login";
		else
			return "redirect:/loggedin";
	 }

	@GetMapping("/loggedin")
	// @PreAuthorize("hasRole('ROLE_EMP') || hasRole('ROLE_ADMIN')")
	public String dashboard(){
		if (AuthenticationSystem.isLoggedAs("ROLE_EMP"))
			return "redirect:/employee/dashboard";
		else if (AuthenticationSystem.isLoggedAs("ROLE_ADMIN"))
			return "redirect:/admin/dashboard?search=null&pg=1";
		else
			return "redirect:/login";
	}
	
	@GetMapping("/family")
	public String family() {
		return "myFamily";
	}
	
	@GetMapping("/dashboardallocation")
	public String dashboardallocation() {
		return "redirect:/admin/dashboardallocation?pg=1";
	}
	
	@GetMapping("/teamallocation")
	public String teamallocation() {
		return "redirect:/admin/teamallocation?pg=1";
	}
	
	@GetMapping("/projectallocation")
	public String projectallocation() {
		return "redirect:/admin/projectallocation?pg=1";
	}
}
