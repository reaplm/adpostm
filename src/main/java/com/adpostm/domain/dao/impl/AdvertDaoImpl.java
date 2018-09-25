package com.adpostm.domain.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.Search;
//import org.hibernate.search.query.dsl.QueryBuilder;

import com.adpostm.domain.dao.AdvertDao;
import com.adpostm.domain.model.Advert;


public class AdvertDaoImpl extends GenericDaoImpl<Advert, Long> implements AdvertDao{

	EntityManager em;

	public AdvertDaoImpl() {
		super(Advert.class);
		em = super.getEntityManager();
	}
	@Override
	@Transactional
	public List<Advert> findByCategory(String category) {
		return null;
	}
	@Override
	@Transactional
	public List<Advert> search(String searchText, Long category) throws InterruptedException {						
		
		return null;
	}
}
