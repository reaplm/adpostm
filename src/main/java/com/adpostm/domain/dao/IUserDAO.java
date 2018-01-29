package com.adpostm.domain.dao;

import java.sql.Date;

import com.adpostm.domain.model.AppUser;

public interface IUserDAO {

	public AppUser getUserByUsername(String username);
	public boolean usernameValid(String username);
	public int createUser(AppUser appUser);
	public void updateUser(AppUser appUser);
	public int updateAddress(String postAddress1, String postAddress2,
			String street, String surbub, String state, String postCoce, 
			String mobileNo, int userDetailId);
	public int updateLastLogin(String username);
}
