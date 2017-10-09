package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface IMenuDAO {
	public List<Menu> getMenusWithoutSub();
	public List<Menu> getMenuByType(String type);
	public Menu getMenuById(int id);
	public void updateMenu(Menu menu);
	public int createMenu(Menu menu);
}
