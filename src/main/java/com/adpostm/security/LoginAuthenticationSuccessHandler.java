package com.adpostm.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.adpostm.domain.model.AppUser;
import com.adpostm.service.UserService;

/**
 * 
 * @author http://www.baeldung.com/spring_redirect_after_login
 * @author http://javapointers.com/tutorial/spring-custom-authenticationsuccesshandler-example-2
 *
 */
public class LoginAuthenticationSuccessHandler 
	implements AuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	UserService userService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) 
					throws IOException,ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
		
	}
	protected void clearAuthenticationAttributes(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	public void setRedirectStrategy(RedirectStrategy redirectStrategy){
		this.redirectStrategy = redirectStrategy;
	}
	protected RedirectStrategy getRedirectStrategy(){
		return this.redirectStrategy;
	}
	protected void handle(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication)
			throws IOException{
		String targetUrl = determineTargetUrl(authentication);
		HttpSession session = request.getSession();
		User authUser = (User)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		AppUser appUser = userService.getUserByUsername(authUser.getUsername());	
		appUser.setLastLoginDate(new Date(System.currentTimeMillis()));
		session.setAttribute("username", authUser.getUsername());
		session.setAttribute("firstName", appUser.getUserDetail().getFirstName());
		session.setAttribute("lastLogin", appUser.getLastLoginDate());
		session.setAttribute("registrationDate", appUser.getRegistrationDate());
		session.setAttribute("authorities", authentication.getAuthorities());
		session.setAttribute("loggedIn", true);
		if(appUser.getUserDetail().getImageCdn() == null) {
			session.setAttribute("profileImage",
					"https://ucarecdn.com/d6ae93a9-bd2f-4ba2-a407-16dbd530a11b/ic_account_circle_black_36dp_2x.png");
		}
		else
			session.setAttribute("profileImage", appUser.getUserDetail().getImageCdn());
		
		
		if(response.isCommitted())
			return;
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}
	protected String determineTargetUrl(Authentication authentication){
		boolean isUser, isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority auth:authorities){
			roles.add(auth.getAuthority());
		}
		if(isAdmin(roles))
			return "/home";
		else if(isUser(roles))
			return "/home";
		else return "/accessDenied";
	}
	private boolean isAdmin(List<String> roles){
		if(roles.contains("ROLE_ADMIN"))
			return true;
		else return false;
	}
	private boolean isUser(List<String> roles){
		if(roles.contains("ROLE_USER"))
			return true;
		else return false;
	}
}
