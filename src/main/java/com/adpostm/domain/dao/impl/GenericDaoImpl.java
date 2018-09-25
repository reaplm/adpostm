package com.adpostm.domain.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.enumerated.PersistenceManager;

public class GenericDaoImpl<T, PK extends Serializable>
	implements GenericDao<T, PK>{
	
	private Class<T> type;

	EntityManager em;
	
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
		setEntityManager();
	}
	public void setEntityManager() {
        this.em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	@Override
	@Transactional(rollbackOn=RuntimeException.class)
	public T create(T newInstance){
		//Session session = em.unwrap(Session.class);
        
		em.getTransaction().begin();
		em.persist(newInstance);
		em.getTransaction().commit();
		return newInstance;
	}

	@Override
	@Transactional
	public T read(PK id) {
		//return (T)getSession().get(type, id);
		return em.find(type, id);
		
	}
	@Override
	@Transactional(rollbackOn=RuntimeException.class)
	public void update(T transientObject) throws Exception{
		//getSession().update(transientObject);		
		em.merge(transientObject);
	}
	@Override
	@Transactional
	public void delete(T persistentObject) throws Exception{
		em.remove(persistentObject);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		return em
				.createQuery("from " +
					clazz.getSimpleName())
				.getResultList();
	}
	public EntityManager getEntityManager() {
		return this.em;
	}
	@Override
	public List<T> findAll(Class<T> clazz, boolean asc, String... orderBy) {
		List<Order> orderList = new ArrayList<Order>();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> advert = cq.from(clazz);
		cq.select(advert);
			
		orderList = Arrays.asList(orderBy)
							.stream()
							.map(a -> advert.get(a))
							.map(s -> asc? cb.asc(s):cb.desc(s))
							.collect(Collectors.toList());
		
		cq.orderBy(orderList);
		
		return em.createQuery(cq).getResultList();
		
	}
}
//https://www.ibm.com/developerworks/java/library/j-genericdao/