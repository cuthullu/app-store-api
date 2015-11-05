package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;

public class GetMethod<T> implements HibernateMethod<Integer, T> {

	static final Logger LOG = LoggerFactory.getLogger(GetMethod.class);
	
	private final Class<T> type;

	public GetMethod(Class<T> typeParameterClass) {
		this.type = typeParameterClass;
	}

	@Override
	public T call(Session session, Integer input) {
		LOG.debug("Getting " + input, type);
		return (T) session.get(type, input);
	}

}
