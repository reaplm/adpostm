package com.adpostm.domain.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.domain.enumerated.PersistenceManager;
import com.adpostm.listener.EMF;

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
	public EntityManager getEntityManager() {
		return this.em;
	}

	

}
//https://www.ibm.com/developerworks/java/library/j-genericdao/