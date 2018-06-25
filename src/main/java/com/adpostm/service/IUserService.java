package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;



public interface IUserService {

	public boolean usernameValid(String username);
	public AppUser getUserByUsername(String username);
	public AppUser getUserById(int userId);
	public int createUser(AppUser appUser);
	public void updateUser(AppUser appUser);
	public int updateAddress(String postAddress1, String postAddress2,
			String street, String surbub, String state, String postCoce, 
			String mobileNo, int userDetailId);
	public int updateLastLogin(String username);
}
