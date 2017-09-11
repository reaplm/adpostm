package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.Menu;

public interface IMenuService {
	public List<Menu> getMenuByType(String type);
	public List<Menu> getMenus();
}
