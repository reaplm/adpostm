package com.adpostm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.dao.IUserDAO;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Role;
import com.adpostm.domain.model.UserDetail;
import com.adpostm.mail.MailAgent;

@Controller
public class RegistrationController {

	@Autowired
	IUserDAO iUserService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registeration(){
		return new ModelAndView("register");
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView submitRegisteration(HttpServletRequest request,
			HttpServletResponse response) throws IOException, AddressException, 
			MessagingException{
		ModelAndView modelAndView = new ModelAndView("register");
		int userId = createAppUser(request.getParameter("fname"),
				request.getParameter("lname"),request.getParameter("email"),
				request.getParameter("password"));
	
		if(userId > 0){
			MailAgent mailAgent = new MailAgent
					(request.getParameter("email"), "pdm.molefe@gmail.com", 
							"pmolefe@bec.co.bw", null,
							"adpostm registration activation", 
							"Congradulations you have successfully registered on adpostm", 
							"localhost");
			
			mailAgent.sendMessage();
			
			modelAndView.addObject("msg","<p  class='bg-info'>You have successfully registered. "
					+ "You will be redirected to the login page.</p>" );
			response.setHeader("refresh", "5;url=/adpostm/login");
		}
		else{
			modelAndView.addObject("msg","<p  class='bg-info'>An internal error occured. "
					+ "Please try again later.</p>" );
		}
		return modelAndView;
	}
	@RequestMapping(value="/usernameValid", method=RequestMethod.POST)
	@ResponseBody
	public String usernameExists(HttpServletRequest request,
			HttpServletResponse response){
		if(iUserService.usernameValid(request.getParameter("email")))
			return "true";
		else return "false";
	}
	private int createAppUser(String firstName, String lastName,
			String email, String password){
		AppUser appUser = new AppUser();
		UserDetail userDetails = new UserDetail();
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		appUser.setEmail(email);
		appUser.setPassword(password);
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastName);
		appUser.setUserDetail(userDetails);
		role.setRoleName("ROLE_USER");
		roles.add(role);
		appUser.setRoles(roles);
		return iUserService.createUser(appUser);
		
	}
	
}
