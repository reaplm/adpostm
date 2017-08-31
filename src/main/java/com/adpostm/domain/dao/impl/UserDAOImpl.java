package com.adpostm.domain.dao.impl;

import java.util.List;

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
	
}
