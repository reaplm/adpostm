package com.adpostm.service.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.controller.Utils.BeanUtility;
import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.dao.MenuDao;
import com.adpostm.domain.enumerated.MenuType;
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
	public Menu create(Menu newInstance){
		return menuDao.create(newInstance);
	}

	@Override
	public Menu read(Long id) {
		return menuDao.read(id);
	}

	@Override
	public void update(Menu transientObject) throws Exception {
		Menu original = read(transientObject.getMenuId());
		
		if(original != null) {			
			Set<String> ignoreFields = BeanUtility.getNullPropertyNames(transientObject);
			BeanUtils.copyProperties(transientObject, original, 
					ignoreFields.toArray(new String[ignoreFields.size()]));

			menuDao.update(original);
		}
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

	@Override
	public boolean checkMenuNameValid(String menuName, Long parentId) {
		return menuDao.checkMenuNameValid(menuName, parentId);
	}
	@Override
	public List<Menu> findAllByMenuTypeIn(List<MenuType> type) {
		return menuDao.findAllByMenuTypeIn(type);
	}
}
