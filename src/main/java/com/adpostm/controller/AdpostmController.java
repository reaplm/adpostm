package com.adpostm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.dao.IMenuDAO;
import com.adpostm.domain.model.Menu;

@Controller
public class AdpostmController {
	@Autowired
	IMenuDAO iMenuDAO;
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("home");
		List<Menu> menus = getMenuByType("home");
		modelAndView.addObject("menus", menus);
		return modelAndView;
	}
	@RequestMapping(value="/admin")
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView("dashboard");
		List<Menu> menus= getMenuByType("sidebar");
		modelAndView.addObject("sideMenu", menus);
		return modelAndView;
	}
	private List<Menu> getMenus(){
		return iMenuDAO.getMenus();
	}
	private List<Menu> getMenuByType(String type){
		return iMenuDAO.getMenuByType(type);
	}
}
