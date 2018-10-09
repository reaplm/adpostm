package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Advert;

public interface AdvertDao extends GenericDao<Advert, Long>{
	public List<Advert> findByCategory(String category);
	public List<Advert> search(String searchText);
	public List<Advert> search(String searchText, Long category);
}
