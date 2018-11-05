package com.adpostm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.Address;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.AdvertService;
import com.adpostm.service.MenuService;
import com.adpostm.service.UserService;

@Controller
public class AdpostmController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdvertService advertService;
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("home");

		return mv;
	}
	
	@RequestMapping(value="/admin/profile")
	public ModelAndView profile(HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("profile");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		AppUser user = null;
		
		if(username != null)
			user = userService.getUserByUsername(username);

		modelAndView.addObject("user", user);
		modelAndView.addObject("address", new Address());
		return modelAndView;
	}
	@RequestMapping(value="/admin/preferences")
	public ModelAndView preferences(){
		ModelAndView modelAndView = new ModelAndView("preferences");
		return modelAndView;
	}
	@RequestMapping(value="/admin/communication")
	public ModelAndView communication(){
		ModelAndView modelAndView = new ModelAndView("communication");
		return modelAndView;
	}
	@RequestMapping(value="/admin/activity")
	public ModelAndView activity(){
		ModelAndView modelAndView = new ModelAndView("activity");
		return modelAndView;
	}
	@RequestMapping(value="/admin/dashboard")
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView("dashboard");

		return modelAndView;
	}
	@RequestMapping(value="/admin/menus")
	public ModelAndView menus(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView("menus");
		model.addObject("menu", new Menu());
		
		return model;
	}
	@RequestMapping(value="/admin/posts")
	public ModelAndView posts(){
		ModelAndView model = new ModelAndView("posts");
		List<Advert> adverts = getAdverts();
		model.addObject("adverts", adverts);

		return model;
	}
	@RequestMapping(value="/admin/users")
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", getAllUsers());
		mav.addObject("sideMenu", findMenuByType(new String[]{"sidebar"}));
		return mav;
	}
	private List<AppUser> getAllUsers() {
		return userService.findAll(AppUser.class, true, new String[] {"appUserId"});
	}
	private List<Menu> findMenuByType(String[] type){
		List<MenuType> menuType = Arrays.asList(type)
										.stream()
										.map(s -> MenuType.valueOf(s.toUpperCase()))
										.collect(Collectors.toList());
		
		return menuService.findAllByMenuTypeIn(menuType);
	}
	private List<Advert> getAdverts(){
		return advertService.findAll(Advert.class);
	}
	private List<Menu> getAllMenus(){
		return menuService.findAll(Menu.class);
	}
}
