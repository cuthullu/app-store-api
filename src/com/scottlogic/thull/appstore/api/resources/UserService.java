package com.scottlogic.thull.appstore.api.resources;

import java.util.List;

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

import com.scottlogic.thull.appstore.api.dao.UserDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateUserDao;
import com.scottlogic.thull.appstore.api.domains.User;

@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserService {
	
	static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	private final UserDao dao = new HibernateUserDao();
	
	@GET
	public Response getAllUser() {
		LOG.debug("GET ALL request");
		List<User> users = dao.getAllUsers();
		return Response.ok(users).build();
	}
	
	@GET
	@Path("{id}")
	public Response getUser(@PathParam("id") Integer id ){
		LOG.debug("Get request: User" + id);
		
		User user = dao.getUser(id);
		
		return Response.ok(user).build();
	}
	
	@POST
	public Response createUser(User user, @Context UriInfo uriInfo) {
		LOG.debug("Post Create user request " + user);
		
		Integer id = dao.createUser(user);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(id.toString());
		return Response.created(builder.build()).entity(user).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateUser(@PathParam("id") Integer id , User user) {
		LOG.debug("Updating User: " + id);
		user.setIdFromRemote(id);
		user = dao.updateUser(user);
		
		return Response.ok().entity(user).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		LOG.debug("Deleting User: " + id);
		
		dao.deleteUser(id);
		
		return Response.noContent().build();
	}
}
