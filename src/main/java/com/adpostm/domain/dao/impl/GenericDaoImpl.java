package com.adpostm.domain.dao.impl;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.Session;

import com.adpostm.domain.dao.GenericDao;
import com.adpostm.hibernate.dao.HibernateUtil;

public class GenericDaoImpl<T, PK extends Serializable>
	implements GenericDao<T, PK>{
	
	private Class<T> type;
	
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}
	@Override
	@Transactional(rollbackOn=RuntimeException.class)
	public PK create(T newInstance) {
		return (PK)getSession().save(newInstance);
	}

	@Override
	@Transactional
	public T read(PK id) {
		return (T)getSession().get(type, id);
	}
	@Override
	@Transactional(rollbackOn=RuntimeException.class)
	public void update(T transientObject) {
		getSession().update(transientObject);
		
	}
	@Override
	public void delete(T persistentObject) {
		getSession().delete(persistentObject);
	}
	protected Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	

}
//https://www.ibm.com/developerworks/java/library/j-genericdao/