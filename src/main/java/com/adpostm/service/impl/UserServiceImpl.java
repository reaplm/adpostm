package com.adpostm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;







import com.adpostm.domain.dao.IUserDAO;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Role;
import com.adpostm.service.IUserService;


public class UserServiceImpl implements UserDetailsService, IUserService{
	@Autowired
	IUserDAO iUserDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//Fetch user from the database
				AppUser user = iUserDAO.getUserByUsername(username);
				if(user == null){
					throw new UsernameNotFoundException("Failed to fetch "
							+ "userDetails from the database.");
				}
					List<GrantedAuthority> authorities = buildUserAuthority(user);
					return buildUserForAuthentication(user, authorities);
	}
	/**
	 * Retrieves user roles from AppUser
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> buildUserAuthority(AppUser user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> userRoles = user.getRoles();
		for(Role role:userRoles){
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}
	/**
	 * Converts com.adpostm.domain.model.AppUser to
	 * org.springframework.security.core.userDetails.User
	 * @param AppUser user
	 * @param List<GrantedAuthority> authorities
	 * @return User
	 */
	private User buildUserForAuthentication(AppUser user, 
			List<GrantedAuthority> authorities){
		return new User(user.getEmail(), user.getPassword(),
				true, true, true, true, authorities);
	}
	@Override
	public boolean usernameValid(String username) {
		return iUserDAO.usernameValid(username);
	}
	@Override
	public int createuser(AppUser appUser) {
		return iUserDAO.createUser(appUser);
	}
	
}
