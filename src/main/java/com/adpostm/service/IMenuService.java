package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.Menu;
import com.adpostm.domain.model.SubMenu;

public interface IMenuService {
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenus();
	public Menu getMenuById(int id);
	public void updateMenu(Menu menu);
	public int createMenu(Menu menu);
	public SubMenu getSubMenuById(int id);
	public int createSubMenu(SubMenu subMenu);
}
