package com.scottlogic.thull.appstore.api.dao.hibernate.bundle;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;
import com.scottlogic.thull.appstore.api.domains.Bundle;

public class CreateMethod implements HibernateMethod<Bundle, Integer>{

	@Override
	public Integer call(Session session, Bundle input) {
		session.beginTransaction();
		Integer id = (Integer) session.save(input);
		session.getTransaction().commit();
		return id;
	}

}
