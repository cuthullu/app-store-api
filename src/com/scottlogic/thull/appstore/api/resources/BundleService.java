package com.scottlogic.thull.appstore.api.resources;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.BundleDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateBundleDao;
import com.scottlogic.thull.appstore.api.domains.Bundle;

@Produces(MediaType.APPLICATION_JSON)
@Path("/bundle")
public class BundleService {
	static final Logger LOG = LoggerFactory.getLogger(BundleService.class);

	private final BundleDao dao = new HibernateBundleDao();

	@GET
	public Response getAllBundles() {
		LOG.debug("GET ALL request");
		List<Bundle> bundles = dao.getAllBundles();
		return Response.ok(bundles).build();
	}

	@GET
	@Path("{id}")
	public Response getBundle(@PathParam("id") Integer id) {
		LOG.debug("GET request: bundle" + id);
		Bundle bundle = dao.getBundle(id);
		LOG.debug("Bundle Found " + bundle.getId() + " " + bundle.getTitle());
		return Response.ok(bundle).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBundle(Bundle bundle, @Context UriInfo uriInfo) throws ConstraintViolationException {
		LOG.debug("creating bundle" + bundle);
		
		Integer id = dao.createBundle(bundle);
		
		LOG.info("Bundle created with id: " + id);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(id.toString());
		return Response.created(builder.build()).entity(bundle).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBundle(@PathParam("id") Integer id ,Bundle bundle) throws ConstraintViolationException{
		LOG.debug("Updating bundle " + id);
		bundle.setIdFromRemote(id);
		dao.updateBundle(bundle);
		return Response.ok().entity(bundle).build();
		
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteBundle(@PathParam("id") Integer id) {
		LOG.debug("Deleting bundle " + id);
		dao.deleteBundle(id);
		return Response.noContent().build();
	}
}
