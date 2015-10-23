package com.scottlogic.thull.appstore.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.exception.mapper.ConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.resources.BundleService;
 
public class ResourceLoader extends Application{
	static final Logger LOG = LoggerFactory.getLogger(ResourceLoader.class);
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        
        LOG.info("ResourceLoader request for classes made");
        
        classes.add(BundleService.class);
        LOG.info("BundleService resource added");
        return classes;
    }
    
    public Set<Object> getSingletons() {
        final Set<Object> classes = new HashSet<Object>();
        
        LOG.info("ResourceLoader request for singletons made");
        
        classes.add(new ConstraintViolationMapper());
        LOG.info("ConstraintViolationMapper resource added");
        return classes;
    }
    
}