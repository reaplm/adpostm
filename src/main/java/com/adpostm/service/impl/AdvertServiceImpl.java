package com.adpostm.service.impl;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;

import com.adpostm.domain.dao.AdvertDao;
import com.adpostm.domain.model.Advert;
import com.adpostm.domain.model.AdvertDetail;
import com.adpostm.service.AdvertService;

public class AdvertServiceImpl implements AdvertService{
	
	@Autowired
	AdvertDao advertDao;

	@Override
	public Advert create(Advert newInstance) {
		return advertDao.create(newInstance);
	}

	@Override
	public Advert read(Long id) {
		return advertDao.read(id);
	}

	@Override
	public void update(Advert transientObject) throws Exception {
		advertDao.update(transientObject);
		
	}

	@Override
	public void delete(Advert persistentObject) throws Exception {
		advertDao.delete(persistentObject);
		
	}

	@Override
	public List<Advert> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Advert> findAll(Class<Advert> clazz) {
		return advertDao.findAll(clazz);
	}

	@Override
	public List<Advert> findAll(Class<Advert> clazz, boolean asc, String... orderBy) {
		return advertDao.findAll(clazz, asc, orderBy);
	}

	@Override
	public List<Advert> search(String searchText) {
		return advertDao.search(searchText);
	}

	@Override
	public List<Advert> search(String searchText, Long category) {
		return advertDao.search(searchText, category);
	}

	@Override
	public boolean checkImageExists(String uuid) {
		return advertDao.checkImageExists(uuid);
	}

	@Override
	public boolean removeAllPictures(Long id) {
		return advertDao.removeAllPictures(id);
	}

	@Override
	public List<Object[]> statusCount() {
		return advertDao.statusCount();
	}

	@Override
	public List<String> findDistinctLocation() {
		return advertDao.findDistinctLocation();
	}

	@Override
	public List<String> findDistinctMonth() {
		return advertDao.findDistinctMonth();
	}

	@Override
	public List<String> findDistinctYear() {
		return advertDao.findDistinctYear();
	}

}
