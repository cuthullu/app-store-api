package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;

public class CreateMethod<T> implements HibernateMethod<T, Integer>{

	@Override
	public Integer call(Session session, T input) {
		session.beginTransaction();
		Integer id = (Integer) session.save(input);
		session.getTransaction().commit();
		return id;
	}

}
