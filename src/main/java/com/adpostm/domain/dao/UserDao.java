package com.adpostm.domain.dao;

import com.adpostm.domain.model.AppUser;

public interface UserDao extends GenericDao<AppUser, Long>{ //

	public AppUser getUserByUsername(String username);
	public boolean usernameValid(String username);
	public int updateAddress(String postAddress1, String postAddress2,
			String street, String surbub, String state, String postCoce, 
			String mobileNo, int userDetailId);
	public boolean updateLastLogin(String username) throws Exception;
}
