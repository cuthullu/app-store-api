package com.scottlogic.thull.appstore.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import com.scottlogic.thull.appstore.api.resources.HelloService;
 
public class ResourceLoader extends Application{
 
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        
        // register root resource
        classes.add(HelloService.class);
        return classes;
    }
}