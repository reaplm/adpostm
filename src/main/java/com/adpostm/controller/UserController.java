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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.domain.enumerated.MenuType;
import com.adpostm.domain.model.AppUser;
import com.adpostm.domain.model.Menu;
import com.adpostm.service.IMenuService;
import com.adpostm.service.IUserService;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class UserController {

	@Autowired
	IUserService iUserService;
	
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
		if(username != null) {
			user = iUserService.getUserByUsername(username);
			if((uuid != null) && (cdnUrl != null) && (imgName != null)) {
				System.out.println(uuid+"->"+cdnUrl+"->"+imgName);
				user.getUserDetail().setImageCdn(cdnUrl);
				user.getUserDetail().setImageName(imgName);
				user.getUserDetail().setImageUuid(uuid);
				iUserService.updateUser(user);
				result = "success";
			}
		}
		if(result.equals("success"))
			session.setAttribute("profileImage", cdnUrl);
		return result;
	}
	@RequestMapping(value="/user/update/address", method=RequestMethod.POST)
	@ResponseBody
	public  void updateAddress(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, JSONException {
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
		JSONObject myObj = new JSONObject();
		int userId = 0;
		if(request.getParameter("userId") != null)
			userId = Integer.parseInt(request.getParameter("userId"));
		
		int result = iUserService.updateAddress(request.getParameter("postAddress1"), 
				request.getParameter("postAddress2"), request.getParameter("street"), 
				request.getParameter("surbub"), request.getParameter("state"), 
				request.getParameter("postCode"), request.getParameter("mobileNo"),
				userId);
		if(result > 0) {
			myObj.put("success", true);
			myObj.put("message", "Address saved successfully!");
		}
		else {
			myObj.put("success", false);
			myObj.put("message", "Record not updated");
		}
		out.println(myObj.toString());
        out.close();
		
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@ResponseBody
	public AppUser getUser(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="username")String username ) {
		AppUser appUser = iUserService.getUserByUsername(username);
		return appUser;
	}
}
