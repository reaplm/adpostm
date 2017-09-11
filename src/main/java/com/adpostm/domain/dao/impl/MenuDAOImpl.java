package com.adpostm.domain.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import com.adpostm.domain.dao.IMenuDAO;
import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;
import com.adpostm.hibernate.dao.HibernateUtil;

public class MenuDAOImpl implements IMenuDAO{

	@Override
	public List<Menu> getMenus() {
		List<Menu> result = null;
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Menu");
			result = (List<Menu>)query.list();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(GenericJDBCException sqlEx){
			System.out.println("GenericJDBCException(public List<Menu> getMenus): " 
					+ sqlEx);
		}
		return result;
	}

	@Override
	public List<Menu> getMenuByType(String type) {
		List<Menu> result = null;
		MenuType menuType = MenuType.valueOf(type.toUpperCase());
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Menu where menuType = :menuType");
			query.setParameter("menuType", menuType);
			result = (List<Menu>)query.list();
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		catch(Exception ex){
			System.out.println("Exception in getMenuByType(String type): " + ex);
		}
		return result;
	}

}
