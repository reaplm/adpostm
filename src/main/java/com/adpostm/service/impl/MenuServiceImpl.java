package com.adpostm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.domain.dao.IMenuDAO;
import com.adpostm.domain.model.Menu;
import com.adpostm.domain.model.SubMenu;
import com.adpostm.service.IMenuService;

public class MenuServiceImpl implements IMenuService{
	@Autowired
	IMenuDAO iMenuDAO;
	
	@Override
	public List<Menu> getMenus() {
		return iMenuDAO.getMenus();
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
	public void updateMenu(Menu menu) {
		iMenuDAO.updateMenu(menu);
		
	}

	@Override
	public int createMenu(Menu menu) {
		return iMenuDAO.createMenu(menu);
		
	}

	@Override
	public SubMenu getSubMenuById(int id) {
		return iMenuDAO.getSubMenuById(id);
	}

	@Override
	public int createSubMenu(SubMenu subMenu) {
		return iMenuDAO.createSubMenu(subMenu);
	}
}
