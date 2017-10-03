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

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.IMenuService;

@Controller
public class MenuController {

	@Autowired
	IMenuService iMenuService;
	
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
	@RequestMapping(value="/menus/add")
	public String submitAddMenu(HttpServletRequest request, HttpServletResponse response) {
		Menu menu = createMenu(request.getParameter("menuName"),
				request.getParameter("menuDesc"),request.getParameter("icon"));
		
		int menuId = iMenuService.createMenu(menu);
		if(menuId > 0)
			return "success";
		else return "failure";
		
	}
	private List<Menu> getMenus(){
		return iMenuService.getMenus();
	}
	private List<Menu> getMenuByType(String type){
		return iMenuService.getMenuByType(type);
	}
	private Menu createMenu(String menuName, String menuDesc,
			String icon) {
		Menu menu = new Menu();
		menu.setMenuName(menuName);
		menu.setMenuDesc(menuDesc);
		menu.setIcon(icon);
		menu.setMenuType(MenuType.HOME);
		return menu;
	}
}
