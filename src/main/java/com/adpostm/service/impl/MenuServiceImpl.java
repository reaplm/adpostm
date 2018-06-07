package com.adpostm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.domain.dao.IMenuDAO;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.IMenuService;

public class MenuServiceImpl implements IMenuService{
	@Autowired
	IMenuDAO iMenuDAO;
	
	@Override
	public List<Menu> getMenusWithoutSub() {
		return iMenuDAO.getMenusWithoutSub();
	}

	@Override
	public List<Menu> getMenuByType(String type) {
		return iMenuDAO.getMenuByType(type);
	}

	@Override
	public Menu getMenuById(int id) {
		return iMenuDAO.getMenuById(id);
	}

	@Override
	public boolean updateMenu(Menu menu) {
		return iMenuDAO.updateMenu(menu);
		
	}

	@Override
	public int createMenu(Menu menu) {
		return iMenuDAO.createMenu(menu);
		
	}

	@Override
	public List<Menu> getMenuList() {
		return iMenuDAO.getMenuList();
	}
}
