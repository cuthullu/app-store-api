package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import org.hibernate.Query;
import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;

public class GetByUniqueFieldMethod<T> implements HibernateMethod<String, T> {

	private String field;

	private final Class<T> type;

	public GetByUniqueFieldMethod(Class<T> typeParameterClass, String field) {
		this.type = typeParameterClass;
		this.field = field;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call(Session session, String input) {
		String queryString = "from " + type.getName() + " where " + 
				field + " = :input";
		Query query = session.createQuery(queryString);
		query.setString("input", input);
		return (T) query.uniqueResult();
	}

}
