package com.adpostm.domain.dao;

import com.adpostm.domain.model.AppUser;

public interface IUserDAO {

	public AppUser getUserByEmail(String email);
}
