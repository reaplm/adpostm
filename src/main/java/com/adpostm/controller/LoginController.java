package com.adpostm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value="error", required=false) boolean error){
		ModelAndView modelAndView = new ModelAndView("login");
		if(error){
			modelAndView.addObject("msg", "<p class='errorMsg'>Invalid email or password</p>");
		}
		return modelAndView;
	}

}
