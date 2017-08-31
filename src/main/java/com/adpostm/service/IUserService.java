package com.adpostm.service;

import com.adpostm.domain.model.AppUser;



public interface IUserService {

	public boolean usernameValid(String username);
	public int createuser(AppUser appUser);
}
