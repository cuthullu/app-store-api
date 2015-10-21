package com.scottlogic.thull.appstore.api.dao.HibernateDao;

import java.util.List;

import org.hibernate.Session;

import com.scottlogic.thull.appstore.api.domains.Bundle;

public class GetAllMethod implements HibernateMethod<Integer, List<Bundle>>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Bundle> call(Session session, Integer input) {
		return session.createQuery("from Bundle").list();
	}


}
