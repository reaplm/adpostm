package com.adpostm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.adpostm.service.UserService;

public class LogoutHandler implements LogoutSuccessHandler{
	@Autowired
	UserService userService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication)throws IOException, ServletException {
		if(authentication != null && authentication.getDetails() != null) {
			String username = (String)request.getSession().getAttribute("username");
			try {
				//Invalidates this session then unbinds any objects bound to it
				request.getSession().invalidate();
				//update database
				boolean result = userService.updateLastLogin(username);
				System.out.println("username: " + username );
			}
			catch(Exception e) {
				System.out.println("Exception in logoutSuccessHandler " + e);
				e.printStackTrace();
			}
		}
			
		response.sendRedirect("/adpostm/login?logout=true");
	}

}
