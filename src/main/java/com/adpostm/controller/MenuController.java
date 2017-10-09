package com.adpostm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.fasterxml.jackson.databind.util.JSONPObject;

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
		String message = "success";
		iMenuService.updateMenu(menu);
		return message;
	}
	@RequestMapping(value="/menus/add")
	@ResponseBody
	public String submitAddMenu(HttpServletRequest request, HttpServletResponse response)
			throws JSONException{
		JSONObject jsonObject = new JSONObject();
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		int menuId = 0;
		String message =  "fail";
		Menu menu = createMenu(request.getParameter("menuName"),
				request.getParameter("menuDesc"),request.getParameter("icon"));

		if(parentId > 0) {//this is a submenu
			Menu parentMenu = iMenuService.getMenuById(parentId);
			menu.setMenu(parentMenu);
			menu.setMenuType(MenuType.SUBMENU);
		}
		else {
			menu.setMenuType(MenuType.HOME);
		}
		menuId = iMenuService.createMenu(menu);
		message = menuId > 0 ? "success":"fail";
		
		return message;
	}
	private List<Menu> getMenusWithoutSub(){
		return iMenuService.getMenusWithoutSub();
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
		return menu;
	}
}
