package org.antwalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.antwalk.model.Movie;
import org.antwalk.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String home()
	{
		return "home";//home bean
	}
	@RequestMapping("welcome")
	public String welcome(HttpServletRequest request)
	{
		String firstName=request.getParameter("firstName");
		request.setAttribute("fName", firstName);
		return "welcome";//welcome bean
	}
	@RequestMapping("user")
	public ModelAndView showUser(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("UserForm");
		User usr=new User();
		Map<String, String> genderMap=new HashMap();
		genderMap.put("male", "Male");
		genderMap.put("female", "Female");
		Map<String, String> countryMap=new HashMap();
		countryMap.put("India", "India");
		countryMap.put("Australia", "Australia");
		countryMap.put("USA", "USA");
		countryMap.put("China", "China");
		mv.addObject("user",usr);
		mv.addObject("country",countryMap);
		mv.addObject("gender",genderMap);
		
		return mv;
	}
	@RequestMapping("userInfo")
	public ModelAndView showUserInfo(@ModelAttribute User user)
	{
		ModelAndView mv=new ModelAndView("userInfo");
		System.out.println(user);
		mv.addObject("usr",user);
		return mv;
	}
}
