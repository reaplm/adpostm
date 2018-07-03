package com.adpostm.service;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.model.AppUser;



public interface UserService extends GenericDao<AppUser, Long>{

	public boolean usernameValid(String username);
	public AppUser getUserByUsername(String username);
	public int updateAddress(String postAddress1, String postAddress2,
			String street, String surbub, String state, String postCoce, 
			String mobileNo, int userDetailId);
	public boolean updateLastLogin(String username) throws Exception;
}
