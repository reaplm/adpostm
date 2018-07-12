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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.adpostm.domain.dao.UserDao;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Role;
import com.adpostm.service.UserService;

public class UserServiceImpl implements UserDetailsService, UserService{
	@Autowired
	UserDao userDao;
	
	public UserServiceImpl() {}
	
	@Override
	public boolean updateLastLogin(String username) throws Exception {
		return userDao.updateLastLogin(username);
	}

	@Override
	public AppUser getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	@Override
	public AppUser create(AppUser newInstance){
		return userDao.create(newInstance);
	}
	@Override
	public AppUser read(Long id) {
		return userDao.read(id);
	}
	@Override
	public void update(AppUser transientObject) throws Exception {
		userDao.update(transientObject);
		
	}
	@Override
	public void delete(AppUser persistentObject) throws Exception {
		userDao.delete(persistentObject);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		//Fetch user from the database
				AppUser user = userDao.getUserByUsername(username);
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
		return userDao.usernameValid(username);
	}

}
