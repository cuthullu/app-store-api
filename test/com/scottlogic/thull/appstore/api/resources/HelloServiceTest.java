package com.scottlogic.thull.appstore.api.resources;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;



public class HelloServiceTest extends JerseyTest {

	@Override
    protected Application configure() {
        return new ResourceConfig(HelloService.class);
    }
 

	@Test
	public void test() {
		final String hello = target("hello").request().get(String.class);
		Assert.assertEquals("hello", hello);
	}

}
