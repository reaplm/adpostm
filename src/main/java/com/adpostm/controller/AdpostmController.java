package com.adpostm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.MenuService;
import com.adpostm.service.UserService;

@Controller
public class AdpostmController {
	@Autowired
	MenuService iMenuService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("home");
		List<Menu> menus = getMenuByType("home");
		modelAndView.addObject("menus", menus);
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/profile")
	public ModelAndView profile(HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("profile");
		List<Menu> menus= getMenuByType("sidebar");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		AppUser user = null;
		
		if(username != null)
			user = userService.getUserByUsername(username);

		modelAndView.addObject("user", user);
		modelAndView.addObject("sideMenu", menus);
		return modelAndView;
	}
	@RequestMapping(value="/admin/preferences")
	public ModelAndView preferences(){
		ModelAndView modelAndView = new ModelAndView("preferences");
		List<Menu> menus= getMenuByType("sidebar");
		modelAndView.addObject("sideMenu", menus);
		return modelAndView;
	}
	@RequestMapping(value="/admin/communication")
	public ModelAndView communication(){
		ModelAndView modelAndView = new ModelAndView("communication");
		List<Menu> menus= getMenuByType("sidebar");
		modelAndView.addObject("sideMenu", menus);
		return modelAndView;
	}
	@RequestMapping(value="/admin/activity")
	public ModelAndView activity(){
		ModelAndView modelAndView = new ModelAndView("activity");
		List<Menu> menus= getMenuByType("sidebar");
		modelAndView.addObject("sideMenu", menus);
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
	private List<Menu> getMenuByType(String type){
		return iMenuService.getMenuByType(type);
	}
}
