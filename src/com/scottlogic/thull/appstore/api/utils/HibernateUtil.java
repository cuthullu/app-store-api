package com.scottlogic.thull.appstore.api.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static final SessionFactory buildSessionFactory() {
		
		LOG.info("Creating hibernate session factory");
		try {
			Configuration cfg = new Configuration().configure();
			cfg.setProperty("hibernate.connection.url", "jdbc:mysql://" + System.getenv("DATABASE_URL"));
			cfg.setProperty("hibernate.connection.username", System.getenv("DATABASE_USER"));
			cfg.setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
			return cfg.buildSessionFactory();
		} catch (Throwable ex) {
			LOG.error("Inital SessionFactory creation failed." + ex);
			throw  new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
