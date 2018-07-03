package com.adpostm.domain.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.dao.MenuDao;
import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;
import com.adpostm.hibernate.dao.HibernateUtil;

public class MenuDaoImpl implements MenuDao{

	@Autowired
	GenericDao<Menu, Long> genericDao;
	
	@Override
	public Long create(Menu newInstance) {
		return genericDao.create(newInstance);
	}

	@Override
	public Menu read(Long id) {
		return genericDao.read(id);
	}

	@Override
	public void update(Menu transientObject) throws Exception {
		genericDao.update(transientObject);
		
	}

	@Override
	public void delete(Menu persistentObject) throws Exception {
		genericDao.delete(persistentObject);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenusWithoutSub() {

		return getSession().createQuery("from Menu"
				+ " where menu is null")
					.list();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenuByType(String type) {
		MenuType menuType = MenuType.valueOf(type.toUpperCase());
		
		return (List<Menu>)getSession().createQuery("from Menu where "
								+ "menuType = :menuType")
								.list();

	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getMenuList(){		
		return (List<Menu>)getSession()
				.createQuery("from Menu")
				.list();

	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
