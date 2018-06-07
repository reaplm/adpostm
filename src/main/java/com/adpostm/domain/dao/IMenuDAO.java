package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface IMenuDAO {
	public List<Menu> getMenusWithoutSub();
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenuList();
	public Menu getMenuById(int id);
	public boolean updateMenu(Menu menu);
	public int createMenu(Menu menu);
}
