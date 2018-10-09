package com.adpostm.domain.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

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
	public List<Advert> search(String searchText, Long category){						
		
		return null;
	}
	@Override
	public List<Advert> search(String searchText) {		
		FullTextEntityManager fullTextEntityManager = 
				org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		em.getTransaction().begin();
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Advert.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
				.keyword()
				.onFields("advertDetail.title","advertDetail.body")
				.matching(searchText)
				.createQuery();
		
		javax.persistence.Query jpaQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery, Advert.class);
		
		List<Advert> results = jpaQuery.getResultList();
		
		em.getTransaction().commit();
		
		
		return results;
	}
}
