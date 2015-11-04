package com.scottlogic.thull.appstore.api.dao.hibernate;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.CreateMethod;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.DeleteMethod;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.GetAllMethod;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.GetMethod;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.UpdateMethod;
import com.scottlogic.thull.appstore.api.domains.Bundle;

public class HibernateBundleDao extends HibernateDao implements BundleDao {
	static final Logger LOG = LoggerFactory.getLogger(HibernateBundleDao.class);

	@Override
	public Integer createBundle(Bundle bundle) throws ConstraintViolationException {
		return callDatabase(bundle, new CreateMethod<Bundle>());
	}

	@Override
	public List<Bundle> getAllBundles() {
		return callDatabase(null, new GetAllMethod<Bundle>(Bundle.class));
	}

	@Override
	public Bundle getBundle(Integer id) {
		LOG.debug("id", id);
		return callDatabase(id, new GetMethod<Bundle>(Bundle.class));
	}
	
	@Override
	public Bundle updateBundle(Bundle bundle) {
		return callDatabase(bundle, new UpdateMethod<Bundle>(Bundle.class));
	}
	
	@Override
	public Boolean deleteBundle(Integer id) {
		return callDatabase(id, new DeleteMethod<Bundle>(Bundle.class));
	}

}
