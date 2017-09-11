package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;



public interface IUserService {

	public boolean usernameValid(String username);
	public int createuser(AppUser appUser);

}
