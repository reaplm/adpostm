package com.adpostm.service;

import com.adpostm.dao.model.AppUser;

public interface IUserService {
	/**
	 * Spring security authentication
	 * @param email
	 * @return
	 */
	public AppUser loadUserByEmail(String email);
}
