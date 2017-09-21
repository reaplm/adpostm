package com.adpostm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@RequestMapping(value="/menus/detail", method=RequestMethod.GET)
	@ResponseBody
	public Menu getMenuDetail(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam("id")int id){
		return iMenuService.getMenuById(id);
	}
	@RequestMapping(value="/menus/update", method=RequestMethod.POST)
	@ResponseBody
	public String submitEditMenu(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("menu") Menu menu) {
		String msg = "success";
		iMenuService.updateMenu(menu);
		return msg;
	}
	private List<Menu> getMenus(){
		return iMenuService.getMenus();
	}
	private List<Menu> getMenuByType(String type){
		return iMenuService.getMenuByType(type);
	}
}
