package com.adpostm.domain.dao;

import com.adpostm.domain.model.AppUser;

public interface UserDao extends GenericDao<AppUser, Long>{ //

	public AppUser getUserByUsername(String username);
	public boolean usernameValid(String username);
	public boolean updateLastLogin(String username) throws Exception;
	
}
