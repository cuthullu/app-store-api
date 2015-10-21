package com.scottlogic.thull.appstore.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.dao.HibernateDao.HibernateBundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;

@Produces(MediaType.APPLICATION_JSON)
@Path("/hello")
public class HelloService {
	
	private final BundleDao dao = new HibernateBundleDao();
	@GET
	public Response getHello(){
		System.out.println("Got before the database shizbizz");
		List<Bundle> bundles = dao.getAllBundles();
		return Response.ok(bundles).build();
	}
}
