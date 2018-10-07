package com.adpostm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.model.Address;
import com.adpostm.domain.model.AppUser;
import com.adpostm.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/updatepic", method=RequestMethod.POST)
	@ResponseBody
	public String updateProfilePic(HttpServletRequest request, 
			HttpServletResponse response){
		HttpSession session = request.getSession();
		String uuid = request.getParameter("uuid");
		String cdnUrl = request.getParameter("cdnUrl");
		String imgName = request.getParameter("name");
		AppUser user = null;
		String result = "fail";
		String username = (String)session.getAttribute("username");
		
		try {
			if(username != null) {
				user = userService.getUserByUsername(username);
				if((uuid != null) && (cdnUrl != null) && (imgName != null)) {
					System.out.println(uuid+"->"+cdnUrl+"->"+imgName);
					user.getUserDetail().setImageCdn(cdnUrl);
					user.getUserDetail().setImageName(imgName);
					user.getUserDetail().setImageUuid(uuid);
					userService.update(user);
					result = "success";
				}
			}
			if(result.equals("success"))
				session.setAttribute("profileImage", cdnUrl);
		}
		catch(Exception ex) {
			System.out.println("Exception caught in updateProfilePic: " + ex);
			ex.printStackTrace();
		}
			return result;
	}
	@RequestMapping(value="/user/update/address", method=RequestMethod.POST)
	@ResponseBody
	public  String updateAddress(HttpServletRequest request, 
			HttpServletResponse response, 
			@ModelAttribute("address")Address address){
		boolean success = false;
		Long userId = 0L;
		AppUser appUser = null;
		try {
				userId = Long.parseLong(request.getParameter("userId"));
				appUser = userService.read(userId);
				
				if(appUser != null) {	
					//address.setUserDetail(appUser.getUserDetail());
					appUser.getUserDetail().setAddress(address);
					userService.update(appUser);
					
					success = true;
				}
		}
		catch(NumberFormatException ex) {
			System.out.println("Invalid userId");
			ex.printStackTrace();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return String.valueOf(success);
	}
	@RequestMapping(value="/user/update/contact", method=RequestMethod.GET)
	@ResponseBody
	public  String updateContact(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("co-userId")Long id){
		boolean success = false;
		AppUser appUser = null;
		try {
				//userId = Long.parseLong(request.getParameter("userId"));
				appUser = userService.read(id);
				
				if(appUser != null) {	
					appUser.getUserDetail().setMobileNo(request.getParameter("mobileNo"));
					userService.update(appUser);
					
					success = true;
				}
		}
		catch(NumberFormatException ex) {
			System.out.println("Invalid userId");
			ex.printStackTrace();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return String.valueOf(success);
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@ResponseBody
	public AppUser getUser(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="username")String username ) {
		AppUser appUser = userService.getUserByUsername(username);
		return appUser;
	}
	@RequestMapping(value="/user/id", method=RequestMethod.GET)
	@ResponseBody
	public AppUser getUser(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="id")Long id ) {
		AppUser appUser = userService.read(id);
		return appUser;
	}
	
}
