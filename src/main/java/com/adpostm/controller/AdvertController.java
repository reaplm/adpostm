package com.adpostm.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.enumerated.AdvertStatus;
import com.adpostm.domain.model.AdPicture;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.AdvertInfo;
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
		model.addObject("advertInfo", new AdvertInfo());
		return model;
	}
	@RequestMapping(value="/advert/search", method=RequestMethod.GET)
	public ModelAndView getAdverts(HttpServletRequest request,
			HttpServletResponse response){
			ModelAndView modelAndView = new ModelAndView("adverts");
			String search = request.getParameter("search");
			String category = request.getParameter("s-category");
			
			List<Advert> advertList = null;
			try {
				Long menuId = Long.parseLong(category);
				
				if(search.isEmpty() && menuId == -1) {
					advertList = advertService.findAll(Advert.class, true, new String[]{"menu"});
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
			HttpServletResponse response, 
			@ModelAttribute("advertInfo")AdvertInfo advertInfo) {
		String msg = "fail";
		try {
			
			Advert advert = createAdvert(advertInfo);
			
			if(advert != null) {
				advertService.create(advert);
				msg = "success";
			}
				
			
		}
		catch(NumberFormatException ex) {
			System.out.println("NumberFormatException in submitAdvert ");
			ex.printStackTrace();
		}
		
		return msg;
	}
	@RequestMapping(value="/advert/detail", method=RequestMethod.GET)
	@ResponseBody
	public Advert getAdvertDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id")Long id) {
		Advert advert = advertService.read(id);
		return advert;
	}
	private Advert createAdvert(AdvertInfo advertInfo) {
		
		Menu subMenu = menuService.read(advertInfo.getSubMenuId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getPrincipal().equals("anonymousUser")){
			return null;
		}
		else{
			User user = (User) auth.getPrincipal();
			AppUser appUser = userService.getUserByUsername(user.getUsername());
		
		
			AdvertDetail advertDetail = new AdvertDetail
											.AdvertDetailBuilder()
											.setTitle(advertInfo.getSubject())
											.setBody(advertInfo.getBody())
											.setContactEmaily(advertInfo.getContactEmail())
											.setContactPhone(advertInfo.getcontactNo())
											.setLocation(advertInfo.getLocation())
											.setGroupCdnUrl(advertInfo.getGroupCdnUrl())
											.setGroupCount(advertInfo.getGroupCount())
											.setGroupUuid(advertInfo.getGroupUuid())
											.setGroupSize(advertInfo.getGroupSize())
											.build();
			
			List<AdPicture> adPictures = getAdPictures(advertInfo);
			
			for(AdPicture adPicture : adPictures ) 
				advertDetail.addAdPicture(adPicture);
			
	
			Advert advert = new Advert.AdvertBuilder()
								.setAdvertDetail(advertDetail)
								.setMenu(subMenu)
								.setAppUser(appUser)
								.setAdvertStatus(AdvertStatus.SUBMITTED)
								.setSubmittedDate(new Date())
								.build();
			
			return advert;
		}
		
		}
	private List<AdPicture> getAdPictures(AdvertInfo advertInfo){
		List<AdPicture> adPictures = new ArrayList<AdPicture>();

		
		for(int i = 0; i< advertInfo.getGroupCount(); i++) {
			AdPicture adPicture = new AdPicture.
									AdPictureBuilder()									
									.setName((advertInfo.getImageName()).get(i))
									.setSize((advertInfo.getImageSize()).get(i))
									.setUuid((advertInfo.getImageUuid()).get(i))
									.setCdnUrl((advertInfo.getImageCdnUrl()).get(i))
									.build();
			adPictures.add(adPicture);		
									
		}
		return adPictures;

	}

}
