package com.adpostm.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Role;
import com.adpostm.domain.model.UserDetail;
import com.adpostm.mail.MailAgent;
import com.adpostm.security.EncryptPassword;
import com.adpostm.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registeration(){
		return new ModelAndView("register");
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView submitRegistration(HttpServletRequest request,
			HttpServletResponse response) throws AddressException, 
			MessagingException{
		ModelAndView modelAndView = new ModelAndView("register");
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Long userId = createAppUser(fName, lName, email, password);
	
		if(userId > 0){
			if(sendMessage(lName, fName, email)) {
				//update notification sent column
				AppUser appUser = userService.read(userId);
				appUser.setNotified(1);
				userService.update(appUser);
				modelAndView.addObject("msg","<p  class='bg-info'>You have successfully registered. "
						+ "You will be redirected to the login page.</p>" );
				response.setHeader("refresh", "5;url=/adpostm/login");
			}
		}
		else{
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			modelAndView.addObject("msg","<p  class='bg-info'>Registration failed. "
					+ "Please try again later.</p>" );
		}
	

		return modelAndView;
	}
	@RequestMapping(value="/usernameValid", method=RequestMethod.POST)
	@ResponseBody
	public String usernameExists(HttpServletRequest request, HttpServletResponse response){
		if(userService.usernameValid(request.getParameter("email")))
			return "true";
		
		else return "false";
	}
	private Long createAppUser(String firstName, String lastName,
			String email, String password){
		List<Role> roles = new ArrayList<Role>();
		Date now = new Date((System.currentTimeMillis()));
		
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		roles.add(role);
		
		UserDetail userDetails = new UserDetail.UserDetailBuilder()
										.setFirstName(firstName)
										.setLastName(lastName)
										.build();
		AppUser appUser = new AppUser.AppUserBuilder()
									.setEmail(email)
									.setPassword(EncryptPassword.getEncryptedPassword(password))
									.setRoles(roles)
									.setUserDetail(userDetails)
									.setRegistrationDate(now)
									.build();
	
		return userService.create(appUser);
		
	}
	/**
	 * MailAgent mailAgent = new MailAgent(to, from, cc, bcc, subject, content, smtphost)
	 * @param fName
	 * @param lName
	 * @param email
	 * @return
	 */
	private boolean sendMessage(String fName, String lName, String email) {
		boolean sent = false;
		try {
			MailAgent mailAgent = new MailAgent
					(email, "pdm.molefe@gmail.com", 
							"pmolefe@bec.co.bw", null,
							"adpostm registration activation", 
							"Congradulations" + fName + " you have "
									+ "successfully registered on adpostm", 
							"localhost");
			
			mailAgent.sendMessage();
			sent = true;
		}
		catch(MessagingException ex) {
			System.out.println("Messaging Exception: Failed to send email to " + fName + " "+ lName);
			ex.printStackTrace();
		}
		return sent;
	}
	
}
