package com.scottlogic.thull.appstore.api.dao.HibernateDao;

import java.util.List;
import org.hibernate.Session;
import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;
import com.scottlogic.thull.appstore.api.utils.HibernateUtil;

public class HibernateBundleDao implements BundleDao {
	
	public HibernateBundleDao() {
		System.out.println("Creating bundle dao");
	}

	public List<Bundle> getAllBundles(){
		return callDatabase(null, new GetAllMethod());	
	}
	
	private <I, O> O callDatabase(I input, HibernateMethod<I, O> method){
		
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
