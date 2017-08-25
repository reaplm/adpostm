package com.adpostm.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static synchronized void initSessionFactory(){
		if(sessionFactory != null){
			throw new IllegalStateException(
					"Session factory already initialized!");
		}
		try{
			//Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = buildSessionFactory();
		}
		catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory buildSessionFactory(){
		try{
			if(sessionFactory == null){
					Configuration configuration = new Configuration().
							configure(HibernateUtil.class.
									getResource("/hibernate.cfg.xml"));
					StandardServiceRegistryBuilder serviceRegistryBuilder
						= new StandardServiceRegistryBuilder();
					serviceRegistryBuilder.applySettings(configuration.getProperties());
					ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
					sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				}
			return sessionFactory;
		}
		catch(Throwable ex){
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void closeSessionFactory(){
		sessionFactory.close();
	}
}
