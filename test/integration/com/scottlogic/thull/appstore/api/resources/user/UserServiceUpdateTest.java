package integration.com.scottlogic.thull.appstore.api.resources.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.scottlogic.thull.appstore.api.domains.User;
import com.scottlogic.thull.appstore.api.exception.mapper.ConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.exception.mapper.OtherConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.resources.UserService;

public class UserServiceUpdateTest extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(UserService.class);
		testApp.register(new ConstraintViolationMapper());
		testApp.register(new OtherConstraintViolationMapper());
		return testApp;
	}

	public User user;
	public String location;

	@Before
	public void before() throws URISyntaxException {
		user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response createResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		user = createResponse.readEntity(User.class);
	}

	@Test
	public void shouldOkOnUpdate() {
		user.setUsername(UserMaker.getNextName());

		final Response response = target(location).request()
				.put(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void shouldReturnObjectOnUpdate() {
		user.setUsername(UserMaker.getNextName());
		
		final Response response = target(location).request()
				.put(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		
		User updatedUser = response.readEntity(User.class);
		Assert.assertEquals(user.getId(), updatedUser.getId());
	}
	
}

