package com.scottlogic.thull.appstore.api.dao;

import java.util.List;

import com.scottlogic.thull.appstore.api.domains.Bundle;

public interface BundleDao {
	
	public List<Bundle> getAllBundles();
	
	public Bundle getBundle(Integer id);
	
	public Integer createBundle(Bundle bundle);
	
	public Bundle updateBundle(Bundle bundle);
	
	public Boolean deleteBundle(Integer id);
}
