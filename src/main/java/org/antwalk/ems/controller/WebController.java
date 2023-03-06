package org.antwalk.ems.controller;

import org.antwalk.ems.security.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
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
			return "redirect:/dashboard";
	 }

	@GetMapping("/dashboard")
	public String dashboard(){
		Long id = AuthenticationSystem.getId();
		if (AuthenticationSystem.isLoggedAs("ROLE_EMP"))
			return "redirect:/employeedashboard?id="+id;
		else
			return "redirect:/admindashboard?id="+id;
	}

	@GetMapping("/employeedashboard")
	public String employeeDashboard(){
		return "employeedashboard";
	}

	@GetMapping(value="/admindashboard")
	public String adminDashboard() {
		return "admindashboard";
	}
	
}
