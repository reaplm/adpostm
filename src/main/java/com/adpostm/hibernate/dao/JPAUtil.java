package com.adpostm.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil{
	private static EntityManagerFactory factory;
	
    
    public static synchronized void initEmFactory(){
		if(factory != null){
			throw new IllegalStateException(
					"Session factory already initialized!");
		}
		try{
			//Create emFactory from persistence.xml 
			factory = Persistence.createEntityManagerFactory("JPAPersistence");
		}
		catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
    public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	public static void closeEmFactory(){
		factory.close();
	}
}
