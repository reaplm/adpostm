package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface IMenuDAO {
	public List<Menu> getMenus();
	public List<Menu> getMenuByType(String type);
}
