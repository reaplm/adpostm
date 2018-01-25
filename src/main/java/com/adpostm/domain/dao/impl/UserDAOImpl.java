package com.adpostm.domain.dao.impl;

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
	@Override
	public AppUser getUserByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<AppUser> result = null;
		session.beginTransaction();
		Query query = session.createQuery("from AppUser "
				+ "where email = :username");
		query.setParameter("username", username);
		result = (List<AppUser>)query.list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		if(result.size() != 1){return null;}
		else {return result.get(0);}
	}

	@Override
	public boolean usernameValid(String username) {
		Long count = -1L;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("select count(*) as count from AppUser "
				+ " where email = :email");
		query.setParameter("email", username);
		count = (Long)query.uniqueResult();
		session.flush();
		session.getTransaction().commit();
		session.close();
		if(count == 0)
			return true;//username is valid
		else
			return false;
	}

	@Override
	public int createUser(AppUser appUser) {
		int userId = -1;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(appUser);
			userId = appUser.getAppUserId();
			session.flush();
			session.getTransaction().commit();
			
		}
		catch(Exception e){
			System.out.println("Error persisting appUser: " + e);
		}
		return userId;
	}

	@Override
	public void updateUser(AppUser appUser) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(appUser);
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex) {
			System.out.println("Exception in updateUser(AppUser appUser): " + ex );
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
			Session session = HibernateUtil.getSessionFactory().openSession();
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
		catch(Exception e) {
			System.out.println("Failed to update address. \n" + e);
			e.printStackTrace();
		}
		return result;
	}
	
}
