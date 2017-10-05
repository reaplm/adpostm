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
import com.adpostm.domain.model.SubMenu;
import com.adpostm.service.IMenuService;

@Controller
public class MenuController {

	@Autowired
	IMenuService iMenuService;
	
	@RequestMapping(value="/menus", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenuDetail(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="type", required=false)String type){
		if(type != null) {
			return  getMenuByType(type);
		}
		else return null;
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
	@RequestMapping(value="/menus/add")
	public String submitAddMenu(HttpServletRequest request, HttpServletResponse response)
	{
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		int menuId = 0;
		String message = "fail";
		if(parentId > 0) {
			SubMenu subMenu = createSubMenu(request.getParameter("menuName"),
					request.getParameter("menuDesc"),request.getParameter("icon"),
					parentId);
			menuId = iMenuService.createSubMenu(subMenu);
			if(menuId > 0)
				message = "success";
			else message = "fail";
		}
		else if(parentId == 0) {
			Menu menu = createMenu(request.getParameter("menuName"),
					request.getParameter("menuDesc"),request.getParameter("icon"));
			menuId = iMenuService.createMenu(menu);
			if(menuId > 0)
				message = "success";
			else message = "fail";
		}
		return message;
		
		
		
		
	}
	@RequestMapping(value="/menus/sub/detail", method=RequestMethod.GET)
	@ResponseBody
	public SubMenu getSubMenuDetail(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam("id")int id){
		return iMenuService.getSubMenuById(id);
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
		menu.setLabel(menuName.replaceAll("\\s+", ""));
		menu.setMenuType(MenuType.HOME);
		return menu;
	}
	private SubMenu createSubMenu(String menuName, String menuDesc,
			String icon, int parentId) {
		Menu menu = iMenuService.getMenuById(parentId);
		SubMenu subMenu = new SubMenu();
		subMenu.setMenuName(menuName);
		subMenu.setMenuDesc(menuDesc);
		subMenu.setLabel(menuName.replaceAll("\\s+", ""));
		subMenu.setMenu(menu);
		return subMenu;
	}
}
