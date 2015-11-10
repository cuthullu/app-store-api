package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;
import com.scottlogic.thull.appstore.api.domains.DomainObject;

public class UpdateMethod<T extends DomainObject<T>> implements HibernateMethod<T, T> {
	static final Logger LOG = LoggerFactory.getLogger(UpdateMethod.class);

	private final Class<T> type;

	public UpdateMethod(Class<T> typeParameterClass) {
		this.type = typeParameterClass;
	}


	@Override
	public T call(Session session, T input) {
		session.beginTransaction();
		T persistedOb = session.get(type, input.getIdFromRemote());
		persistedOb.update(input);
		session.update(persistedOb);
		session.getTransaction().commit();
		return input;
	}

}
