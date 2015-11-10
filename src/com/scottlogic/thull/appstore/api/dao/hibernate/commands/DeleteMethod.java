package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;

public class DeleteMethod<T extends Object> implements HibernateMethod<Integer, Boolean> {
	
	private final Class<T> type;

	public DeleteMethod(Class<T> typeParameterClass) {
		this.type = typeParameterClass;
	}

	
	@Override
	public Boolean call(Session session, Integer input) {
		session.beginTransaction(); 
		T persistedBundle =  session.get(type, input);
		session.delete(persistedBundle);
		session.getTransaction().commit();
		
		return true;
	}

}
