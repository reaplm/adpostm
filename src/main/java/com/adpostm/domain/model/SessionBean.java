package com.adpostm.domain.model;

import java.util.List;

import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class SessionBean {
	private List<Menu> sideMenu;
	private List<Menu> homeMenu;
	
	public void setSideMenu(List<Menu> sideMenu) {
		this.sideMenu = sideMenu;
	}
	public List<Menu> getSideMenu() {
		return this.sideMenu;
	}
	public void setHomeMenu(List<Menu> homeMenu) {
		this.homeMenu = homeMenu;
	}
	public List<Menu> getHomeMenu() {
		return this.homeMenu;
	}
}
