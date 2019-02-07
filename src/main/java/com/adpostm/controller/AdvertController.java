package com.adpostm.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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

import com.adpostm.controller.Utils.Uploadcare;
import com.adpostm.domain.enumerated.AdvertStatus;
import com.adpostm.domain.enumerated.MenuType;
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
	///adpostm/advert/search?category=house for rent&category=house to wanted&
	//category=mobile phones&location=Extension 14&location=mogoditshane&year=2019&image=true
	/**
	 * Search using given criteria
	 * @param fCategory
	 * @param fLocation
	 * @param fYear
	 * @param fImage
	 * @param f
	 * @return
	 */
	@RequestMapping(value="/advert/search", method=RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value="s", required=false)String s,
			@RequestParam(value="category", required=false)List<String> fCategory,
			@RequestParam(value="location", required=false)List<String> fLocation,
			@RequestParam(value="year", required=false)List<Integer> fYear,
			@RequestParam(value="image", required=false)boolean fImage,
			@RequestParam(value="f", required=true)boolean f){
		ModelAndView mv = new ModelAndView("search");
		
		List<Advert> advertList = null;
		List<String> location = advertService.findDistinctLocation();
		List<String> year = advertService.findDistinctYear();
		List<Menu> menu = findMenuByType(new String[] {"home","submenu"});
		
		List<Menu> category = menu.stream()
				.filter(o -> o.getMenuType().equals(MenuType.HOME))
				.collect(Collectors.toList());
	
		String searchMsg = "";
		
		if(s != null) {
			advertList = advertService.search(s);
			searchMsg = "Search result for <span style = 'font-weight: bold'>"+s+"</span>";

			if(f) {
				advertList = advertList.stream()
						.filter(a -> {
								if(fLocation != null) 
									return fLocation.contains(a.getAdvertDetail().getLocation().toLowerCase());
								else return true;
							}
						)
						.filter(
								a -> {
									if(fCategory != null) 
										return fCategory.contains(a.getMenu().getMenuName().toLowerCase());
									else return true;
								}
						)
						.filter(a -> {
									if(fImage)
										return a.getAdvertDetail().getAdPicture().size() > 0;
									else return true;
								}
						)
						.filter(a -> {
								if(fYear != null) {
									Calendar date = Calendar.getInstance();
									date.setTime(a.getSubmittedDate());
									return fYear.contains(date.get(Calendar.YEAR));
								}
								else {return true; }
							}
						)
						.collect(Collectors.toList());
			
			}
			
		}

		mv.addObject("advertList", advertList);
		mv.addObject("locations", location);
		mv.addObject("years", year);
		mv.addObject("categories", category);
		mv.addObject("searchMsg", searchMsg);
		mv.addObject("search", s);
		mv.addObject("fCategory", fCategory);
		mv.addObject("fLocation", fLocation);
		mv.addObject("fYear", fYear);
		mv.addObject("fImage", fImage);
		
		return mv;
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
	/**
	 * Editing an advert...
	 * 	1. Use the id to fetch the object from the database
	 * 	2. Update simple information (title, body, location, contact)
	 * 	3. Update images as follows
	 * 		a. Images are contained in a group, if the groupUuid has not changed
	 * 		 	that means no images were added or deleted therefore do nothing
	 * 		Otherwise....
	 * 		b. Remove images currently attached to this advert
	 * 			-> remove from the database
	 * 			-> remove from online storage
	 * 		c. Create a list of images from form data and attach to the advert
	 * 	  	d. Update group information 
	 * 	4. Fetch the menu this advert belongs to 
	 * 	5. If the menu has changed fetch the new menu
	 * 	6. Update the advert with the new menu
	 * 	7. Persist the advert
	 * @param request
	 * @param response
	 * @param advertInfo bean containing form data
	 * @return
	 */
	@RequestMapping(value="/advert/edit/submit", method=RequestMethod.POST)
	@ResponseBody
	public String submitEditAdvert(HttpServletRequest request, 
			HttpServletResponse response, 
			@ModelAttribute("advertInfo")AdvertInfo advertInfo) {
		String msg = "fail";
		JSONObject ucareResponse;
		List<AdPicture> adPictures = null;
		List<String> uuid = null;
		try {
			
			Advert advert = advertService.read(advertInfo.getAdvertId());
			if(advert != null) {
				//Update form fields
  				advert.getAdvertDetail().setTitle(advertInfo.getSubject());
				advert.getAdvertDetail().setBody(advertInfo.getBody());
				advert.getAdvertDetail().setContactEmail(advertInfo.getContactEmail());
				advert.getAdvertDetail().setContactPhone(advertInfo.getContactNo());
				advert.getAdvertDetail().setLocation(advert.getAdvertDetail().getLocation());
				
				//Update images only if they have changed
				if(advert.getAdvertDetail().getGroupUuid() == null ||
						advert.getAdvertDetail().getGroupUuid().equals(advertInfo.getGroupUuid()) == false) {
					
					//Delete images
					if(advert.getAdvertDetail().getAdPicture().size() > 0) {
						
						uuid = new ArrayList<String>();
						
						for(AdPicture picture:advert.getAdvertDetail().getAdPicture())
							uuid.add(picture.getUui());
						
						//Remove from the database
						advertService.removeAllPictures(advert.getAdvertId());
						
						//Remove from storage
						ucareResponse = deleteFromStorage(uuid);

	
					}
										
					//Replace with new Images
					adPictures = getAdPictures(advertInfo);
					for(AdPicture adPicture:adPictures) {
						advert.getAdvertDetail().addAdPicture(adPicture);
					}
					
					//Replace group Information
					if(adPictures.size() > 0) {
						advert.getAdvertDetail().setGroupCdnUrl(advertInfo.getGroupCdnUrl());
						advert.getAdvertDetail().setGroupCount(advertInfo.getGroupCount());
						advert.getAdvertDetail().setGroupSize(advertInfo.getGroupSize());
						advert.getAdvertDetail().setGroupUuid(advertInfo.getGroupUuid());
					}
				}

				//Check if menu has change
				if(advert.getMenu().getMenuId() != advertInfo.getSubMenuId()){
					Menu menu = menuService.read(advertInfo.getSubMenuId());
					if(menu != null) {
						advert.setMenu(menu);
					}
				}
				
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
	@RequestMapping(value="/adverts")
	public ModelAndView getAdverts() {
		ModelAndView mv = new ModelAndView("adverts");
		
		mv.addObject("homeMenu", findMenuByType(new String[] {"home"}));
		
		return mv;
	}
	@RequestMapping(value="/advert/statusCount")
	@ResponseBody
	public List<Object[]> advertsPerCategory() {
		return advertService.statusCount();
	}
	@RequestMapping(value="/advert/location")
	@ResponseBody
	public List<String> findDistinctLocation() {
		return advertService.findDistinctLocation();
	}
	@RequestMapping(value="/advert/year")
	@ResponseBody
	public List<String> findDistinctYear() {
		return advertService.findDistinctYear();
	}
	@RequestMapping(value="/advert/month")
	@ResponseBody
	public List<String> findDistictMonth() {
		return advertService.findDistinctMonth();
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
				advertInfo.setSubMenuId(advert.getMenu().getMenuId());
				advertInfo.setGroupCdnUrl(advert.getAdvertDetail().getGroupCdnUrl());
				advertInfo.setGroupCount(advert.getAdvertDetail().getGroupCount());
				advertInfo.setGroupUuid(advert.getAdvertDetail().getGroupUuid());
				
				Menu subMenu = menuService.read(advert.getMenu().getMenuId());//submenu
				Menu parentMenu = menuService.read(subMenu.getMenu().getMenuId());
				
				advertInfo.setMenuId(parentMenu.getMenuId());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return advertInfo;
	}
	/**
	 * Delete a batch of images from UCare storage
	 * @param uuid
	 * @return
	 */
	private JSONObject deleteFromStorage(List<String> uuidGroup) {
		Uploadcare ucare = new Uploadcare();
		
		return ucare.deleteBatch(uuidGroup);
	}
	/**
	 * Find menus by type
	 * @param type
	 * @return
	 */
	private List<Menu> findMenuByType(String[] type){
		List<MenuType> menuType = Arrays.asList(type)
										.stream()
										.map(s -> MenuType.valueOf(s.toUpperCase()))
										.collect(Collectors.toList());
		
		return menuService.findAllByMenuTypeIn(menuType);
	}
	
	
}
