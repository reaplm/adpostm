package com.adpostm.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.Order;

public interface GenericDao<T, PK extends Serializable> {
	/**
	 * Persist a new object into the database
	 * @param newInstance
	 * @return
	 */
	T create(T newInstance);
	/**
	 * Retrieve an object from the database with the given primary key
	 * @param id
	 * @return
	 */
	T read(PK id);
	/**
	 * Save changes made to a persistent object
	 * @param transientObject
	 * @return
	 */
	void update(T transientObject) throws Exception;
	/**
	 * Remove an object from the database
	 * @param persistentObject
	 * @return
	 * @throws Exception 
	 */
	void delete(T persistentObject) throws Exception;
	
	/**
	 * Retrieve a list of all object of class T
	 * @param clazz
	 * @return
	 */
	List<T> findAll(Class<T> clazz);
	/**
	 * 
	 * @param clazz
	 * @param order
	 * @param pro
	 * @return
	 */
	List<T> findAll(Class<T> clazz, boolean asc, String... orderBy);
}
