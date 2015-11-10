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
import org.junit.Test;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.scottlogic.thull.appstore.api.domains.User;
import com.scottlogic.thull.appstore.api.exception.mapper.ConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.exception.mapper.OtherConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.resources.UserService;

public class UserServiceReadTest extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(UserService.class);
		testApp.register(new ConstraintViolationMapper());
		testApp.register(new OtherConstraintViolationMapper());
		return testApp;
	}

	@Test
	public void shouldReturnListOfUsers() {
		User user = UserMaker.makeMeAUser();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");

		target("users").request().post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));

		final Response response = target("users").request().get();

		User[] users = response.readEntity(User[].class);

		Assert.assertEquals(200, response.getStatus());
		Assert.assertTrue(users.length > 0);
	}

	@Test
	public void shouldGetUserFromLocation() throws URISyntaxException {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response createResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		user = createResponse.readEntity(User.class);
		final Response response = target(location).request().get();

		User gottedUser = response.readEntity(User.class);

		Assert.assertEquals(user.getUsername(), gottedUser.getUsername());
		Assert.assertEquals(user.getId(), gottedUser.getId());
		Assert.assertEquals(user.getEmail(), gottedUser.getEmail());
	}

	@Test
	public void shouldReturnNotFoundForNonExistantUser() {
		final Response response = target("users/notaid").request().get();
		Assert.assertEquals(404, response.getStatus());
	}

	@Test
	public void shouldNotHaveHashOrSaltInReturnObject() throws URISyntaxException {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response createResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		user = createResponse.readEntity(User.class);
		final Response response = target(location).request().get();

		User gottedUser = response.readEntity(User.class);

		Assert.assertNull(gottedUser.getSalt());
		Assert.assertNull(gottedUser.getPassword());
	}

}
