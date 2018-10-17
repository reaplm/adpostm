package com.adpostm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String result = "fail";
		
		try {
			menuService.update(menu);
			result = "success";
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
	@RequestMapping(value="/menu/edit/status")
	@ResponseBody
	public String updateMenuStatus(HttpServletRequest request,@RequestParam("id")Long id,
			@RequestParam("checked")boolean checked) {
		HttpSession session = request.getSession();
		String result = "fail";
		Menu menu = menuService.read(id);
		
		try {
			if(menu != null) {
				if(checked) {
					menu.setMenuStatus(1);
				}
				else{
					List<Menu> subMenus = menu.getSubMenu();
					
					menu.setMenuStatus(0);
					
					//update submenus
					for(Menu subMenu:menu.getSubMenu())
						subMenu.setMenuStatus(0);
	
					
				}
				menuService.update(menu);
				updateSessionAttribute(session);
				result = "success";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	@RequestMapping(value="/menu/edit/admin")
	@ResponseBody
	public String updateMenuAdminFlag(HttpServletRequest request,
			@RequestParam("id")Long id, @RequestParam("checked")boolean checked) {
		String result = "fail";
		HttpSession session = request.getSession();
		Menu menu = menuService.read(id);
		
		try {
			if(menu != null) {
				if(checked) {
					menu.setAdminMenu(1);
					
					//update submenus
					for(Menu subMenu:menu.getSubMenu())
						subMenu.setAdminMenu(1);
				}
				else{					
					menu.setAdminMenu(0);					
				}
				menuService.update(menu);
				updateSessionAttribute(session);
				result = "success";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
	private void updateSessionAttribute(HttpSession session) {
		List<Menu> menuList = menuService.findAll(Menu.class);
		List<Menu> homeMenu = menuList.stream()
								.filter(s -> s.getMenuType().equals(MenuType.valueOf("HOME")))
								.collect(Collectors.toList());
		List<Menu> sideMenu = menuList.stream()
				.filter(s -> s.getMenuType().equals(MenuType.valueOf("SIDEBAR")))
				.collect(Collectors.toList());
		
		session.setAttribute("homeMenu", homeMenu);
		session.setAttribute("sideMenu", sideMenu);
	}
}
