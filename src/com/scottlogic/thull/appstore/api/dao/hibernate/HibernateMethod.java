package com.scottlogic.thull.appstore.api.dao.hibernate;

import org.hibernate.Session;

public interface HibernateMethod<I, O> {
	
	public O call(Session session, I input);
}
