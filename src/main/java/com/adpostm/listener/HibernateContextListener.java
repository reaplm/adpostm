package com.adpostm.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.adpostm.hibernate.dao.HibernateUtil;


public class HibernateContextListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent sce){
		HibernateUtil.buildSessionFactory();
		System.out.println("HIBERNATE SESSION FACTORY INITIALIZED");
	}
	public void contextDestroyed(ServletContextEvent sce){
		if(HibernateUtil.getSessionFactory() != null){
			HibernateUtil.getSessionFactory();
		}
		
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
		
	}
}
