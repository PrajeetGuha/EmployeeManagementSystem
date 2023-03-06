package org.antwalk.ems.controller;

import org.antwalk.ems.security.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
		if (AuthenticationSystem.isLoggedAs("ROLE_USER"))
			return "redirect:/userdashboard?userid="+id;
		else
			return "redirect:/admindashboard?userid="+id;
	}
}
