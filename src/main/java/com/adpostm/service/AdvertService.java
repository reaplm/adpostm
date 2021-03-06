package com.adpostm.service;

import java.util.List;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;


public interface AdvertService extends GenericDao<Advert, Long>{
	public List<Advert> findByCategory(String category);
	public List<Advert> search(String searchText);
	public List<Advert> search(String searchText, Long category);
	public boolean checkImageExists(String uuid);
	public boolean removeAllPictures(Long id);
	public List<Object[]> statusCount();
	public List<String> findDistinctLocation();
	public List<String> findDistinctYear();
	public List<String> findDistinctMonth();
}
