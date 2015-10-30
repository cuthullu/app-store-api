package com.scottlogic.thull.appstore.api.dao.hibernate;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.domains.Bundle;

public class UpdateMethod implements HibernateMethod<Bundle,Bundle> {
	static final Logger LOG = LoggerFactory.getLogger(UpdateMethod.class);
	@Override
	public Bundle call(Session session, Bundle input) {
		session.beginTransaction();
		Bundle persistedBundle =  session.get(Bundle.class, input.getOutsideId());
		persistedBundle.update(input);
		session.update(persistedBundle);
		session.getTransaction().commit();
		return persistedBundle;
	}

}
