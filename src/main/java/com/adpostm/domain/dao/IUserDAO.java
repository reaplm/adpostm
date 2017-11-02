package com.adpostm.domain.dao;

import com.adpostm.domain.model.AppUser;

public interface IUserDAO {

	public AppUser getUserByUsername(String username);
	public boolean usernameValid(String username);
	public int createUser(AppUser appUser);
	public void updateUser(AppUser appUser);
}
