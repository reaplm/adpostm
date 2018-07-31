package com.adpostm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.enumerated.AdvertStatus;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.AdvertService;
import com.adpostm.service.MenuService;
import com.adpostm.service.UserService;

@Controller
public class AdvertController {
	@Autowired
	AdvertService advertService;
	@Autowired
	MenuService menuService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/advert/newpost")
	public ModelAndView newAdvert() {
		ModelAndView model = new ModelAndView("postAd");
		return model;
	}
	@RequestMapping(value="/advert/search", method=RequestMethod.GET)
	public ModelAndView getAdverts(HttpServletRequest request,
			HttpServletResponse response){
			ModelAndView modelAndView = new ModelAndView("adverts");
			String search = request.getParameter("search");
			List<Advert> advertList = null;
			try {
				Long menuId = Long.parseLong(request.getParameter("s-category"));
				
				if(search.isEmpty() && menuId == -1) {
					advertList = advertService.findAll();
					modelAndView.addObject("advertList", advertList);
					
					if(advertList == null) {
						modelAndView.addObject("searchError", "Your search for the keyword '"
								+search+"' did not return anything");
					}
				}

				else
					modelAndView.addObject("advertList", advertService.search(search, menuId));
				
			}catch(InterruptedException ex) {
				System.out.println("Exception caught during search: " + ex);
				modelAndView.addObject("searchError", "Error occured during search");
			}
			catch(NumberFormatException ex) {
				System.out.println("Exception caught during search: " + ex);
				modelAndView.addObject("searchError", "NumberFormatException caught during search");
			}
		
			return modelAndView;
	}
	@RequestMapping(value="/advert/add", method=RequestMethod.POST)
	@ResponseBody
	public String submitAdvert(HttpServletRequest request, 
			HttpServletResponse response) {
		String msg = "fail";
		try {
			Long menuId = Long.parseLong(request.getParameter("menuId"));
			Long subMenuId = Long.parseLong(request.getParameter("subMenuId"));
			Advert advert = createAdvert(menuId, subMenuId, request.getParameter("adLocation"),
					request.getParameter("adSubject"), request.getParameter("adBody"),
					request.getParameter("contactNo"),request.getParameter("contactEmail"));
			
			if(advert != null) 
				msg = "success";
			
		}
		catch(NumberFormatException ex) {
			System.out.println("NumberFormatException in submitAdvert ");
			ex.printStackTrace();
		}
		
		return msg;
	}
	private Advert createAdvert(Long menuId, Long subMenuId, String location,
			String subject, String body, String contactNo, String contactEmail) {
		
		Menu subMenu = menuService.read(subMenuId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getPrincipal().equals("anonymousUser")){
			return null;
		}
		else{
			User user = (User) auth.getPrincipal();
			AppUser appUser = userService.getUserByUsername(user.getUsername());
		
		
			AdvertDetail advertDetail = new AdvertDetail
											.AdvertDetailBuilder()
											.setTitle(subject)
											.setBody(body)
											.setContactEmaily(contactEmail)
											.setContactPhone(contactNo)
											.setLocation(location)
											.build();
	
			Advert advert = new Advert.AdvertBuilder()
								.setAdvertDetail(advertDetail)
								.setMenu(subMenu)
								.setAppUser(appUser)
								.setAdvertStatus(AdvertStatus.SUBMITTED)
								.setSubmittedDate(new Date())
								.build();
			
			return advertService.create(advert);
		}
		

	}
}
