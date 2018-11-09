package com.adpostm.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.FormSubmitEvent.MethodType;

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

import com.adpostm.domain.dao.AdvertDao;
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
			modelAndView.addObject("search", request.getParameter("search"));
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
				else if(search.isEmpty() && menuId > 0) {}
				else if(!search.isEmpty()  && menuId == -1) {
					advertList = advertService.search(search)
							.stream()
							.sorted((a1, a2) -> a1.getMenu().getMenuName()
									.compareTo(a2.getMenu().getMenuName()))
							.collect(Collectors.toList());
					
					modelAndView.addObject("advertList", advertList);
				}
				else if(!search.isEmpty() && menuId > 0) {
					advertList = advertService.search(search, menuId);
					
					modelAndView.addObject("advertList", advertList);
				}
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
	@RequestMapping(value="/advert/edit/submit", method=RequestMethod.POST)
	@ResponseBody
	public String submitEditAdvert(HttpServletRequest request, 
			HttpServletResponse response, 
			@ModelAttribute("advertInfo")AdvertInfo advertInfo) {
		String msg = "fail";
		try {
			
			Advert advert = advertService.read(advertInfo.getAdvertId());
			
			if(advert != null) {
				advert.getAdvertDetail().setTitle(advertInfo.getSubject());
				advert.getAdvertDetail().setBody(advertInfo.getBody());
				advert.getAdvertDetail().setContactEmail(advertInfo.getContactEmail());
				advert.getAdvertDetail().setContactPhone(advertInfo.getContactNo());
				advert.getAdvertDetail().setLocation(advert.getAdvertDetail().getLocation());
				advert.getAdvertDetail().setGroupCdnUrl(advert.getAdvertDetail().getGroupCdnUrl());
				advert.getAdvertDetail().setGroupCount(advertInfo.getGroupCount());
				advert.getAdvertDetail().setGroupSize(advertInfo.getGroupSize());
				advert.getAdvertDetail().setGroupUuid(advertInfo.getGroupUuid());
				advert.getAdvertDetail().setAdPicture(getAdPictures(advertInfo));
				advert.getMenu().setMenuId(advertInfo.getSubMenuId());
				advertService.update(advert);
				msg = "success";
			}
				
			
		}
		catch(NumberFormatException ex) {
			System.out.println("NumberFormatException in editAdvert ");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception during editAdvert: "+ ex);
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
	
	@RequestMapping(value="/advert/edit/status", method=RequestMethod.GET)
	@ResponseBody
	public String updateStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id")Long id, 
			@RequestParam("checked")String checked) {
		boolean success = false;		
			try {
				Advert advert = advertService.read(id);
				
				if(advert != null) {
					if(checked.equals("true")) {
						advert.setAdvertStatus(AdvertStatus.APPROVED);
						advert.setApprovedDate(new Date(System.currentTimeMillis()));
					}
					else {
						advert.setAdvertStatus(AdvertStatus.REJECTED);
						advert.setRejectedDate(new Date(System.currentTimeMillis()));
					}
						advertService.update(advert);
						success = true;
				}
			}
			catch(Exception ex) {
				System.out.println("Exception when updating status. " + ex);
			}
		return String.valueOf(success);
	}
	@RequestMapping(value="/advert/edit", method=RequestMethod.GET)
	public ModelAndView editAdvert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id")Long id) {
		ModelAndView modelAndView = new ModelAndView("editAd");
		
			
			AdvertInfo advertInfo = getAdvertInfo(id);
			modelAndView.addObject("advertInfo", advertInfo);
		
		
		return modelAndView;
		
	}
	@RequestMapping(value="/advert/edit/detail", method=RequestMethod.GET)
	public String submitEditAdvert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id")Long id) {
		
				return null;
		
	}
	@RequestMapping(value="/advert/delete")
	@ResponseBody
	public String deleteAdvert(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("id")Long id) {
		String result = "fail";
		try {
			if(id > 0) {
				Advert advert = advertService.read(id);
				
				if(advert != null) {
						advertService.delete(advert);
						result = "success";
					} 
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
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
											.setContactEmail(advertInfo.getContactEmail())
											.setContactPhone(advertInfo.getContactNo())
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
	private AdvertInfo getAdvertInfo(Long advertId) {
		Advert advert = advertService.read(advertId);
		AdvertInfo advertInfo = null; 
		try {
			if(advert != null) {
				advertInfo = new AdvertInfo();
				advertInfo.setAdvertId(advert.getAdvertId());
				advertInfo.setSubject(advert.getAdvertDetail().getTitle());
				advertInfo.setBody(advert.getAdvertDetail().getBody());
				advertInfo.setContactEmail(advert.getAdvertDetail().getContactEmail());
				advertInfo.setContactNo(advert.getAdvertDetail().getContactPhone());
				advertInfo.setLocation(advert.getAdvertDetail().getLocation());
				advertInfo.setGroupCdnUrl(advert.getAdvertDetail().getGroupCdnUrl());
				advertInfo.setGroupCount(advert.getAdvertDetail().getGroupCount());
				advertInfo.setGroupSize(advert.getAdvertDetail().getGroupSize());
				advertInfo.setGroupUuid(advert.getAdvertDetail().getGroupUuid());
				advertInfo.setSubMenuId(advert.getMenu().getMenuId());
				
				Menu subMenu = menuService.read(advert.getMenu().getMenuId());//submenu
				Menu parentMenu = menuService.read(subMenu.getMenu().getMenuId());
				
				advertInfo.setMenuId(parentMenu.getMenuId());
				List<AdPicture>  adPictures = advert.getAdvertDetail().getAdPicture();
				List<String> cdnUrl = adPictures
								.stream()
								.map(ad -> ad.getCdnUrl())
								.collect(Collectors.toList());
				
				advertInfo.setImageCdnUrl(cdnUrl);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return advertInfo;
	}
}
