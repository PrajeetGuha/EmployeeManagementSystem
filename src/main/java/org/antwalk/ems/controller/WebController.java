package org.antwalk.ems.controller;

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
	 	return "login";
	 }

	@GetMapping("/dashboard")
	public String dashboard(){
		return "dashboard";
	}
}
