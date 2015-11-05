package com.scottlogic.thull.appstore.api.exception.mapper;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class OtherConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException>{
	
	
	static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationMapper.class);
	@Override
	public Response toResponse(ConstraintViolationException arg0) {
		LOG.info("Constraint Violation thrown: "  + arg0.getCause().getMessage());
		return Response.status(400).entity(arg0.getCause().getMessage()).type(MediaType.APPLICATION_JSON).build();
	}
	
}