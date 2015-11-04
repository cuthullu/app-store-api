package com.scottlogic.thull.appstore.api.dao.hibernate.commands;

import java.util.List;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateMethod;
import com.scottlogic.thull.appstore.api.domains.DomainObject;

public class GetAllMethod<T extends DomainObject<T>> implements HibernateMethod<Integer, List<T>> {

	private final Class<T> type;

	public GetAllMethod(Class<T> typeParameterClass) {
		this.type = typeParameterClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> call(Session session, Integer input) {
		System.out.println("\n\n");
		System.out.println("///////////////////////////////////////");
		System.out.println(type);
		System.out.println("///////////////////////////////////////");
		System.out.println("\n\n");
		return session.createQuery("from " + type.getName()).list();
	}

}
