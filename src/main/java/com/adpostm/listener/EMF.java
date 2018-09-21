package com.adpostm.listener;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class EMF implements ServletContextListener{
	private static EntityManagerFactory emFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//createEmFactory();
		System.out.println("CONTEXT INITIALIZED");
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {	

		//this manually de-registers JDBC driver, which prevents 
		//Tomcat 7 from complaining about memory leaks wrto this class
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()){
			Driver driver = drivers.nextElement();
			try{
				DriverManager.deregisterDriver(driver);
				System.out.println("De-registering jdbc driver: "+ driver);
			}
			catch(SQLException e){
				System.out.println("Error de-registering jdbc driver: "+ driver);
			}
		}
		
		String delme = event.getServletContext().getInitParameter("eraseOnExit");
		if (delme != null && delme.length() > 0) {
		    File del = new File(delme);
		    if (del.exists()) {
		        System.out.println("Deleting file " + delme);
		        del.delete();
		    }
		}
	}

	private void createEmFactory(){
		if(emFactory != null){
			throw new IllegalStateException(
					"Entity Manager Factory already initialized!");
		}
		try{
			//Create emFactory from persistence.xml 
			emFactory = Persistence.createEntityManagerFactory("JPAPersistence");
		}
		catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
    public static EntityManager getEntityManager(){
    	 if (emFactory == null) {
             throw new IllegalStateException("Context is not initialized yet.");
         }
		return emFactory.createEntityManager();
	}
	public static void closeEmFactory(){
		emFactory.close();
	}
}
