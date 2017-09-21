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

	@Override
	public Menu getMenuById(int id) {
		List<Menu> result = null;
		Menu menu = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Menu where menuId = :menuId");
			query.setParameter("menuId", id);
			result = (List<Menu>)query.list();
			session.flush();
			session.getTransaction().commit();
			session.close();
			if(result.size() == 1){menu = result.get(0);}
			else {menu = null;}
			
		}
		catch(Exception ex) {
			System.out.println("Exception caught in getMenuById(int id).\n Exception is" + ex);	
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.update(menu);
			session.flush();
			session.getTransaction().commit();
			session.close();
			
		}
		catch(Exception ex) {
			System.out.println("Exception in updateMenu(Menu menu).\nError thrown is: " + ex );
		}
	}
}
