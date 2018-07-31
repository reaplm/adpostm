package com.adpostm.domain.dao.impl;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adpostm.domain.dao.UserDao;
import com.adpostm.domain.model.AppUser;


@Repository
public class UserDaoImpl extends GenericDaoImpl<AppUser, Long> implements UserDao { 
	
	private EntityManager em;
	
	public UserDaoImpl() {
		super(AppUser.class);
		em = super.getEntityManager();
	}
	
	@Override
	@Transactional
	public boolean usernameValid(String username) {
		Long count = -1L;
		boolean isValid = false;
		
		try {
				count = (Long)em
						.createQuery("select count(*) as count from AppUser "
								+ "where email = :email")
						.setParameter("email", username)
						.getSingleResult();
		
			if(count == 0)
				isValid = true;//username is valid
		}
		catch(NonUniqueResultException ex) {
			System.out.println("NonUnique Result in usernameValid: " + ex);
			ex.printStackTrace();
		}
		return isValid;
	}
	@Override
	@Transactional(rollbackOn=RuntimeException.class)
	public boolean updateLastLogin(String username) throws Exception {
		boolean success = false;
		AppUser appUser = null;

		try {
				appUser = (AppUser)em
					.createQuery("from AppUser where email = :username")
					.setParameter("username", username)
					.getSingleResult();

			if(appUser != null) {
				appUser.setLastLoginDate(new Date(System.currentTimeMillis()));
				update(appUser);
				success = true;
			}
		}
		catch(NonUniqueResultException ex) {
			System.out.println("NonUnique Result in updateLastLogin: " + ex);
			ex.printStackTrace();
		}
		return success;	
	}
	@Override
	@Transactional
	public AppUser getUserByUsername(String username) {
		AppUser appUser = null;
		
		try {
			appUser = (AppUser)em
							.createQuery("from AppUser where email = :username")
							.setParameter("username", username)
							.getSingleResult();
		}
		catch(NonUniqueResultException ex) {
			System.out.println("NonUnique Result in getUserByUsername: " + ex);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return appUser;
	}
}
