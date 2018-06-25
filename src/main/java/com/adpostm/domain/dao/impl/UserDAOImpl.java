package com.adpostm.domain.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;

import com.adpostm.domain.dao.IUserDAO;
import com.adpostm.domain.model.AppUser;
import com.adpostm.hibernate.dao.HibernateUtil;

public class UserDAOImpl implements IUserDAO{
	/**
	 * 
	 */
	Session session = null;
	
	@Override
	public AppUser getUserByUsername(String username) {
		session = HibernateUtil.getSessionFactory().openSession();
		List<AppUser> result = null;
		AppUser appUser = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from AppUser "
					+ "where email = :username");
			query.setParameter("username", username);
			result = (List<AppUser>)query.list();
			session.flush();
			session.getTransaction().commit();
	
			if(result.size() != 1)
				appUser = null;
			
			else appUser = result.get(0);
		}
		catch(Exception sqlException) {
			System.out.println("Error in getUserByUsername: " + sqlException);
			sqlException.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		return appUser;
	}

	@Override
	public boolean usernameValid(String username) {
		Long count = -1L;
		boolean isValid = false;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("select count(*) as count from AppUser "
					+ " where email = :email");
			query.setParameter("email", username);
			count = (Long)query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
		
			if(count == 0)
				isValid = true;//username is valid
		}
		catch(Exception sqlException) {
			System.out.println("Error in getUserByUsername: " + sqlException);
			sqlException.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		return isValid;
	}

	@Override
	public int createUser(AppUser appUser) {
		int userId = -1;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(appUser);
			userId = appUser.getAppUserId();
			session.flush();
			session.getTransaction().commit();
			
		}
		catch(Exception ex){
			//rollback transaction
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			
			System.out.println("Error persisting appUser: " + ex);
			ex.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		return userId;
	}

	@Override
	public void updateUser(AppUser appUser) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(appUser);
			session.flush();
			session.getTransaction().commit();
		}
		catch(Exception sqlException) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
				
			System.out.println("Error in updateUser: " + sqlException);
			sqlException.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public int updateAddress(String postAddress1, String postAddress2,
			String street, String surbub, String state, String postCode, 
			String mobileNo, int userId) {
		int result = 0;
		String queryString = "update UserDetail set postAddress1 = :postAddress1, "
						+ " postAddress2 = :postAddress2," 
						+ "	street = :street, surbub = :surbub, "
						+ " state = :state, postCode = :postCode, " 
						+ "	mobileNo = :mobileNo"
						+ " where userDetailId = :userDetailId";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from AppUser"
					+ " where appUserId = :userId");
			query.setParameter("userId", userId);
			AppUser appUser = (AppUser)query.list().get(0);
			appUser.getUserDetail().setPostAddress1(postAddress1);
			appUser.getUserDetail().setPostAddress2(postAddress2);
			appUser.getUserDetail().setStreet(street);
			appUser.getUserDetail().setSurbub(surbub);
			appUser.getUserDetail().setState(state);
			appUser.getUserDetail().setPostcode(postCode);
			appUser.getUserDetail().setMobileNo(mobileNo);
			session.update(appUser);
			session.getTransaction().commit();
			session.close();
			result = 1;
		}
		catch(Exception sqlException) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
				
			System.out.println("Error in updateAddress: " + sqlException);
			sqlException.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		return result;
	}

	@Override
	public int updateLastLogin(String username) {
		int result = 0;
		AppUser appUser = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from AppUser"
					+ " where email = :username");
			query.setParameter("username", username);
			appUser = (AppUser)query.list().get(0);
			if(appUser != null) {
				appUser.setLastLoginDate(new Date(System.currentTimeMillis()));
				session.update(appUser);
				result = 1;
			}
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e) {
			System.out.println("Exception during update of lastLogin " + e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public AppUser getUserById(int userId) {
		AppUser user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from AppUser where appUserId = :userId");
			query.setParameter("üserId", userId);
		}
		catch(Exception sqlException) {
			System.out.println("Error in getUserById: " + sqlException);
			sqlException.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		return user;
	}

	@Override
	public void deleteUser(AppUser appUser) {
		// TODO Auto-generated method stub
		
	}
	
}
