package com.adpostm.service.impl;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.dao.MenuDao;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.MenuService;

public class MenuServiceImpl implements MenuService{
	@Autowired
	MenuDao menuDao;
	

	
	@Override
	public List<Menu> getMenusWithoutSub() {
		return menuDao.getMenusWithoutSub();
	}

	@Override
	public List<Menu> getMenuByType(String type) {
		return menuDao.getMenuByType(type);
	}

	@Override
	public List<Menu> getMenuList() {
		return menuDao.getMenuList();
	}

	@Override
	public Menu create(Menu newInstance){
		return menuDao.create(newInstance);
	}

	@Override
	public Menu read(Long id) {
		return menuDao.read(id);
	}

	@Override
	public void update(Menu transientObject) throws Exception {
		menuDao.update(transientObject);
		
	}

	@Override
	public void delete(Menu persistentObject) throws Exception {
		menuDao.delete(persistentObject);
		
	}

	@Override 
	public List<Menu> getSubMenus(Long parentId) {
		return menuDao.getSubMenus(parentId);
	}
	@Override
	public List<Menu> findAll(Class<Menu> clazz, boolean asc, String... orderBy) {
		return menuDao.findAll(clazz, asc, orderBy);
	}

	@Override
	public List<Menu> findAll(Class<Menu> clazz) {
		return menuDao.findAll(clazz);
	}
}
