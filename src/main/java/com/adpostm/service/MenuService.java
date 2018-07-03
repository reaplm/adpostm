package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.model.Menu;

public interface MenuService extends GenericDao<Menu, Long> {
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenusWithoutSub();
	public List<Menu> getMenuList();
}
