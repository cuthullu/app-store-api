package com.scottlogic.thull.appstore.api.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static final SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			System.out.println(System.getenv("DATABASE_URL"));
			cfg.setProperty("hibernate.connection.url", "jdbc:mysql://" + System.getenv("DATABASE_URL"));
			cfg.setProperty("hibernate.connection.username", System.getenv("DATABASE_USER"));
			cfg.setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
			return cfg.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Inital SessionFactory creation failed." + ex);
			throw  new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
