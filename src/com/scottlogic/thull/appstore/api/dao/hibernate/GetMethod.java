package com.scottlogic.thull.appstore.api.dao.hibernate;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.domains.Bundle;

public class GetMethod implements HibernateMethod<Integer, Bundle> {

	@Override
	public Bundle call(Session session, Integer input) {
		return (Bundle) session.get(Bundle.class, input);
	}

}
