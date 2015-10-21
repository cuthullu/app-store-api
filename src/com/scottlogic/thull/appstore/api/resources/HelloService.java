package com.scottlogic.thull.appstore.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/hello")
public class HelloService {
	
	@GET
	public Response getHello(){
		return Response.ok("hello").build();
	}
}
