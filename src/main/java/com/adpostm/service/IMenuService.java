package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface IMenuService {
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenusWithoutSub();
	public Menu getMenuById(int id);
	public void updateMenu(Menu menu);
	public int createMenu(Menu menu);
}
