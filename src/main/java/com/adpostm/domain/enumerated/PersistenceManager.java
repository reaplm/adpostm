package com.adpostm.domain.enumerated;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;

public enum PersistenceManager {
	INSTANCE;
	
	private EntityManagerFactory emf;
	
	private PersistenceManager() {
		emf = Persistence.createEntityManagerFactory("JPAPersistence");
	}
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	public void close() {
		emf.close();
	}
}
