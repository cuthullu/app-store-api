package com.scottlogic.thull.appstore.api.dao.hibernate;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;
import com.scottlogic.thull.appstore.api.utils.HibernateUtil;

public class HibernateBundleDao implements BundleDao {
	static final Logger LOG = LoggerFactory.getLogger(HibernateBundleDao.class);

	@Override
	public Integer createBundle(Bundle bundle) throws ConstraintViolationException {
		return callDatabase(bundle, new CreateMethod());
	}

	@Override
	public List<Bundle> getAllBundles() {
		return callDatabase(null, new GetAllMethod());
	}

	@Override
	public Bundle getBundle(Integer id) {
		return callDatabase(id, new GetMethod());
	}
	
	@Override
	public Bundle updateBundle(Bundle bundle) {
		return callDatabase(bundle, new UpdateMethod());
	}
	
	@Override
	public Boolean deleteBundle(Integer id) {
		return callDatabase(id, new DeleteMethod());
	}

	private <I, O> O callDatabase(I input, HibernateMethod<I, O> method) {
		LOG.debug("Calling database using hibernate with: " + method.getClass().getSimpleName());
		Session session = HibernateUtil.getSessionFactory().openSession();
		O out = method.call(session, input);
		session.flush();
		session.close();
		return out;
	}
}
