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
	
	@RequestMapping(value="/admin/dashboard")
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView("dashboard");
		List<Menu> menus= getMenuByType("sidebar");
		modelAndView.addObject("sideMenu", menus);
		return modelAndView;
	}
	@RequestMapping(value="/admin/menus")
	public ModelAndView menus(){
		ModelAndView model = new ModelAndView("menus");
		model.addObject("catMenu", getMenuByType("home"));
		model.addObject("adminMenu", getMenuByType("sidebar"));
		List<Menu> menus= getMenuByType("sidebar");
		model.addObject("sideMenu", menus);
		return model;
	}
	@RequestMapping(value="/admin/posts")
	public ModelAndView posts(){
		ModelAndView model = new ModelAndView("posts");
		List<Menu> menus= getMenuByType("sidebar");
		model.addObject("sideMenu", menus);
		return model;
	}
	@RequestMapping(value="/admin/users")
	public ModelAndView users(){
		ModelAndView model = new ModelAndView("users");
		List<Menu> menus= getMenuByType("sidebar");
		model.addObject("sideMenu", menus);
		return model;
	}
	private List<Menu> getMenus(){
		return iMenuDAO.getMenus();
	}
	private List<Menu> getMenuByType(String type){
		return iMenuDAO.getMenuByType(type);
	}
}
