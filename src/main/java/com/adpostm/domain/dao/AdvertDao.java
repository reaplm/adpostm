package com.adpostm.domain.dao;

import java.util.List;

import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;

public interface AdvertDao extends GenericDao<Advert, Long>{
	public List<Advert> findAll();
	public List<Advert> findByCategory(String category);
	public List<Advert> search(String searchText, Long category) 
			throws InterruptedException;
}
