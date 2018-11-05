package com.adpostm.domain.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.beans.BeanUtils;

import com.adpostm.controller.Utils.BeanUtility;
import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.enumerated.PersistenceManager;
import com.adpostm.domain.model.Menu;
import com.mysql.cj.api.Session;

public class GenericDaoImpl<T, PK extends Serializable>
	implements GenericDao<T, PK>{
	
	private Class<T> type;

	EntityManager em;
	
	public GenericDaoImpl(Class<T> type){
		this.type = type;
		setEntityManager();
		
	}
	public GenericDaoImpl() {
		//createInitialIndex();
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
		em.flush();
		em.clear();
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
	public void update(T transientObject) throws Exception{
			em.getTransaction().begin();
			em.merge(transientObject);
			em.flush();
			em.getTransaction().commit();
	}
	@Override
	public void delete(T persistentObject) throws Exception{
		em.getTransaction().begin();
		em.remove(persistentObject);
		em.flush();
		em.clear();
		em.getTransaction().commit();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
	
		List<T> resultSet =  em
				.createQuery("from " +
					clazz.getSimpleName())
				.getResultList();
	
		
		return resultSet;
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
	public T getObject(T object, PK id) {
		if(id != null) {
			T original = read(id);
			Set<String> ignoreFields = BeanUtility.getNullPropertyNames(original);
			BeanUtils.copyProperties(original, object, 
					ignoreFields.toArray(new String[ignoreFields.size()]));
		}
		return object;
	}
	public EntityManager getEntityManager() {
		return this.em;
	}
	private void createInitialIndex() {
		try {
			FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextSession(em.unwrap(org.hibernate.Session.class));
		
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			System.out.println("Exception creating index.");
			e.printStackTrace();
		}
	}
}
//https://www.ibm.com/developerworks/java/library/j-genericdao/