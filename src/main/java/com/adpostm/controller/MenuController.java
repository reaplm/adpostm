package com.adpostm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	MenuService menuService;
	
	/**
	 * Get a list of menus based on type of menu.
	 * If the type is null return all the menu
	 * @param request
	 * @param response
	 * @param type optional - type of menu
	 * @return
	 */
	@RequestMapping(value="/menus", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenuDetail(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="type", required=false)String type){
		List<Menu> menus = getAllMenus();
		
		if(type != null && menus != null) {
			return  menus.stream()
					.filter(s->s.getMenuType().equals(MenuType.valueOf(type.toUpperCase())))
					.collect(Collectors.toList());
		}

		else return menus;
	}
	@RequestMapping(value="/menus/submenus", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getSubMenus(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("parentId")Long parentId){
		return menuService.getSubMenus(parentId);
	}
	@RequestMapping(value="/menus/detail", method=RequestMethod.GET)
	@ResponseBody
	public Menu getMenuDetail(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam("id")Long id){
		return menuService.read(id);
	}
	@RequestMapping(value="/menus/update", method=RequestMethod.POST)
	@ResponseBody
	public String submitEditMenu(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("menu") Menu menu) {
		String result = "success";
		try {
			menuService.update(menu);
		}
		catch(Exception ex) {
			result = "fail";
		}
		return result;
	}
	@RequestMapping(value="/menus/add")
	public 	@ResponseBody String submitAddMenu(HttpServletRequest request,
			HttpServletResponse response){
		String message =  "fail";
		AppUser appUser = null;
		try {
			Long parentId = Long.parseLong(request.getParameter("parentId"));
			
			Menu menu = createMenu(request.getParameter("menuName"),
					request.getParameter("menuDesc"),request.getParameter("icon"));
	
			if(parentId > 0) {//this is a submenu
				Menu parentMenu = menuService.read(parentId);
				menu.setMenu(parentMenu);
				menu.setMenuType(MenuType.SUBMENU);
			}
			else {
				menu.setMenuType(MenuType.HOME);
			}
			menu = menuService.create(menu);
			message = menu == null ? "fail":"success";
		}
		catch(NumberFormatException ex) {
			System.out.println("Exception caught: " + ex);
			message = "Parent id is invalid";
		}
		return message;
	}
	private List<Menu> getAllMenus(){
		return menuService.getMenuList();
	}
	private Menu createMenu(String menuName, String menuDesc,
			String icon) {
		Menu menu = new Menu.MenuBuilder()
					.setMenuName(menuName)
					.setMenuDesc(menuDesc)
					.setIcon(icon)
					.setLabel(menuName.replaceAll("\\s+", "")).build();
		return menu;
	}
}
