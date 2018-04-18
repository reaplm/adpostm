package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.model.Advert;


public interface IAdvertService {
	public List<Advert> getAdvertByCategory(String category);
	public Advert getAdvertById(int id);
	public void updateAdvert(Advert advert);
	public int createAdvert(Advert advert);
}
