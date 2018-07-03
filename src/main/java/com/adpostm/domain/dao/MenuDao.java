package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface MenuDao extends GenericDao<Menu, Long>{
	public List<Menu> getMenusWithoutSub();
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenuList();
}
