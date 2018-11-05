package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;

public interface MenuDao extends GenericDao<Menu, Long>{
	public List<Menu> getMenusWithoutSub();
	public List<Menu> findAllByMenuTypeIn(List<MenuType> type);
	public List<Menu> getSubMenus(Long parentId);
	public boolean checkMenuNameValid(String menuName, Long parentId);
}
