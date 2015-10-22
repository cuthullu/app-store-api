package com.scottlogic.thull.appstore.api.dao.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;
import com.scottlogic.thull.appstore.api.utils.HibernateUtil;

public class HibernateBundleDao implements BundleDao {
	static final Logger LOG = LoggerFactory.getLogger(HibernateBundleDao.class);
	
	public List<Bundle> getAllBundles(){
		return callDatabase(null, new GetAllMethod());	
	}
	
	private <I, O> O callDatabase(I input, HibernateMethod<I, O> method){
		LOG.debug("Calling database using hibernate with: " + method.getClass().getSimpleName());
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	return method.call(session, input);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
	}
}
