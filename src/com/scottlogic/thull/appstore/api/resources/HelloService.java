package com.scottlogic.thull.appstore.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateBundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces(MediaType.APPLICATION_JSON)
@Path("/hello")
public class HelloService {
	static final Logger LOG = LoggerFactory.getLogger(HelloService.class);
	
	private final BundleDao dao = new HibernateBundleDao();
	
	@GET
	public Response getHello(@Context Request request){
		LOG.debug("GET ALL request");
		List<Bundle> bundles = dao.getAllBundles();
		return Response.ok(bundles).build();
	}
}
