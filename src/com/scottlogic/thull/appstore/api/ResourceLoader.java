package com.scottlogic.thull.appstore.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.resources.HelloService;
 
public class ResourceLoader extends Application{
	static final Logger LOG = LoggerFactory.getLogger(ResourceLoader.class);
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        
        LOG.info("ResourceLoader request for classes made");
        
        classes.add(HelloService.class);
        
        LOG.info("HelloService resource added");
        return classes;
    }
}