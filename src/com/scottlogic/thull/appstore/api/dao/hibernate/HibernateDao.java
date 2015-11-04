package com.scottlogic.thull.appstore.api.dao.hibernate;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.utils.HibernateUtil;

public abstract class HibernateDao {
	static final Logger LOG = LoggerFactory.getLogger(HibernateDao.class);
	
	protected <I, O> O callDatabase(I input, HibernateMethod<I, O> method) {
		LOG.debug("Calling database using hibernate with: " + method.getClass().getSimpleName());
		Session session = HibernateUtil.getSessionFactory().openSession();
		O out = method.call(session, input);
		session.flush();
		session.close();
		return out;
	}
}
