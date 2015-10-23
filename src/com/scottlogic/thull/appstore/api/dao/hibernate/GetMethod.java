package com.scottlogic.thull.appstore.api.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.domains.Bundle;

public class GetMethod implements HibernateMethod<Integer, Bundle> {

	@Override
	public Bundle call(Session session, Integer input) {
		String queryString = "from Bundle where id = :id";
		Query query = session.createQuery(queryString);
		query.setInteger("id", input);
		return (Bundle) query.uniqueResult();
	}

}
