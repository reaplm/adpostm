package com.adpostm.domain.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.dao.UserDao;
import com.adpostm.domain.model.AppUser;
import com.adpostm.hibernate.dao.HibernateUtil;

public class UserDaoImpl implements UserDao { 

	Session session = null;

	@Autowired
	GenericDao<AppUser, Long> genericDao;
	

	@Override
	public Long create(AppUser newInstance) {
		return genericDao.create(newInstance);
	}
	@Override
	public AppUser read(Long id) {
		return genericDao.read(id);
	}
	@Override
	public void update(AppUser transientObject) throws Exception {
		genericDao.update(transientObject);
		
	}
	@Override
	public void delete(AppUser persistentObject) throws Exception {
		genericDao.delete(persistentObject);
	}
	@Override
	@Transactional
	public boolean usernameValid(String username) {
		Long count = -1L;
		boolean isValid = false;
		
		try {
				count = (Long)getSession()
						.createQuery("select count(*) as count from AppUser "
								+ "where email = :email")
						.setParameter("email", username)
						.uniqueResult();
		
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
	@Transactional(rollbackOn=RuntimeException.class)
	public boolean updateLastLogin(String username) throws Exception {
		boolean success = false;
		AppUser appUser = null;

		try {
				appUser = (AppUser)getSession()
					.createQuery("from AppUser where email = :username")
					.setParameter("username", username)
					.uniqueResult();

			if(appUser != null) {
				appUser.setLastLoginDate(new Date(System.currentTimeMillis()));
				update(appUser);
				success = true;
			}
		}
		catch(NonUniqueResultException ex) {
			System.out.println("NonUnique Result in updateLastLogin: " + ex);
		}
		return success;	
	}
	@Override
	@Transactional
	public AppUser getUserByUsername(String username) {
		AppUser appUser = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			appUser = (AppUser)getSession()
								.createQuery("from AppUser where email = :username")
								.setParameter("username", username)
								.uniqueResult();
		}
		catch(NonUniqueResultException ex) {
			System.out.println("NonUnique Result in getUserByUsername: " + ex);
		}
		return appUser;
	}
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
