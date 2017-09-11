package com.adpostm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.model.Menu;
import com.adpostm.service.IMenuService;

@Controller
public class MenuController {

	@Autowired
	IMenuService iMenuService;
	
	@RequestMapping(value="/menus")
	public ModelAndView menus(){
		ModelAndView model = new ModelAndView("menus");
		model.addObject("catMenu", getMenuByType("home"));
		model.addObject("adminMenu", getMenuByType("sidebar"));
		return model;
	}
	private List<Menu> getMenus(){
		return iMenuService.getMenus();
	}
	private List<Menu> getMenuByType(String type){
		return iMenuService.getMenuByType(type);
	}
}
