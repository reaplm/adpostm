package com.adpostm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.enumerated.MenuType;
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
			@RequestParam(value="type", required=false)String[] type){
		List<Menu> menus = null;
		if(type != null) {
			menus = findMenuByType(type);
		}
		else{menus = findAll();}
		return menus;
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
	@ResponseBody
	public 	String submitAddMenu(HttpServletRequest request, HttpServletResponse response){
		
		String message =  "fail";
		List<Menu> homeMenu = null;
		List<Menu> sideMenu = null;
		
		try {
			Long parentId = Long.parseLong(request.getParameter("addParentId"));
			
			Menu menu = createMenu(request.getParameter("addMenuName"),
					request.getParameter("addMenuDesc"),request.getParameter("addIcon"));
	
			if(parentId > 0) {//this is a submenu
				Menu parentMenu = menuService.read(parentId);
				menu.setMenu(parentMenu);
				menu.setMenuType(MenuType.SUBMENU);
			}
			else {
				menu.setMenuType(MenuType.HOME);
			}
			
			menu = menuService.create(menu);
			
			if(menu !=null) {
				homeMenu = findMenuByType(new String[] {"home"});
				sideMenu = findMenuByType(new String[] {"sidebar"});
				
				//Update session variables
				request.getSession().setAttribute("homeMenu", homeMenu);
				request.getSession().setAttribute("sideMenu", sideMenu);
				
				message = "success";
			}
			
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
		String result = "fail";
		Menu menu = menuService.read(id);
		
		try {
			if(menu != null) {
				if(checked) {
					menu.setMenuStatus(1);
				}
				else{					
					menu.setMenuStatus(0);
					
					//update submenus
					for(Menu subMenu:menu.getSubMenu())
						subMenu.setMenuStatus(0);
	
					
				}
				menuService.update(menu);
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
				result = "success";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="/menu/delete")
	@ResponseBody
	public String deleteMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id")Long id) throws IOException {
		ModelAndView mv = new ModelAndView("menus");
		
		List<Menu> homeMenu = null;
		List<Menu> sideMenu = null;
		String result = "fail";
		try {
			Menu menu = menuService.read(id);
			
			if(menu != null) {
				menuService.delete(menu);
				homeMenu = findMenuByType(new String[] {"home"});
				sideMenu = findMenuByType(new String[] {"sidebar"});
				
				//Update session variables
				request.getSession().setAttribute("homeMenu", homeMenu);
				request.getSession().setAttribute("sideMenu", sideMenu);
				
				result = "success";
				
			} 
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}
	@RequestMapping(value="/menutype")
	@ResponseBody
	public List<MenuType> getMenuType(){
		List<MenuType> menuType = new ArrayList<MenuType>(Arrays.asList(MenuType.values()));
		
		return menuType;
	}
	@RequestMapping(value="/menuNameValid")
	@ResponseBody
	public String checkMenuNameValid(@RequestParam("menuName")String menuName,
			@RequestParam("parentId")Long parentId) {
		boolean valid = menuService.checkMenuNameValid(menuName, parentId);

		return String.valueOf(valid);
	}
	@RequestMapping(value="/adsPerCategory")
	@ResponseBody
	public List<Object[]> advertsPerCategory() {
		return menuService.countAdsPerCategory();
	}
	private List<Menu> findAll(){
		return menuService.findAll(Menu.class);
	}
	private List<Menu> findMenuByType(String[] type){
		List<MenuType> menuType = Arrays.asList(type)
									.stream()
									.map(s -> MenuType.valueOf(s.toUpperCase()))
									.collect(Collectors.toList());
		
		return menuService.findAllByMenuTypeIn(menuType);
	}
	private Menu createMenu(String menuName, String menuDesc,
			String icon) {
		Menu menu = new Menu.MenuBuilder()
					.setMenuName(menuName)
					.setMenuDesc(menuDesc)
					.setIcon(icon)
					.setLabel(menuName.replaceAll("\\s+", ""))
					.setAdminMenu(1)
					.setMenuStatus(0)
					.build();
		return menu;
	}
}
