package com.adpostm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adpostm.service.IAdvertService;

@Controller
public class AdvertController {
	@Autowired
	IAdvertService iAdvertService;
	
	@RequestMapping(value="/advert/newpost")
	public ModelAndView newAdvert() {
		ModelAndView model = new ModelAndView("postAd");
		return model;
	}
}
