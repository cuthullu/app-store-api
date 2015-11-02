package com.scottlogic.thull.appstore.api.dao.hibernate.bundle;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;
import com.scottlogic.thull.appstore.api.domains.Bundle;

public class DeleteMethod implements HibernateMethod<Integer, Boolean> {

	@Override
	public Boolean call(Session session, Integer input) {
		session.beginTransaction();
		Bundle persistedBundle =  session.get(Bundle.class, input);
		session.delete(persistedBundle);
		session.getTransaction().commit();
		
		return true;
	}

}
