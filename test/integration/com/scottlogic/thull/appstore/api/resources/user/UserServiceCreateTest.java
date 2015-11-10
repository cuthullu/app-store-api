package integration.com.scottlogic.thull.appstore.api.resources.user;

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

public class UserServiceCreateTest extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(UserService.class);
		testApp.register(new ConstraintViolationMapper());
		testApp.register(new OtherConstraintViolationMapper());
		return testApp;
	}

	/**
	 * Creation tests
	 */
	@Test
	public void shouldCreateObjectOkAndLocation() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, response.getStatus());
		Assert.assertNotNull(response.getHeaderString("location"));
	}

	@Test
	public void shouldReturnCreatedObject() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		User responseUser = response.readEntity(User.class);

		Assert.assertEquals(user.getEmail(), responseUser.getEmail());
		Assert.assertEquals(user.getUsername(), responseUser.getUsername());
	}

	@Test
	public void shouldNotReturnHashOrSaltFromCreatedObject() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		User responseUser = response.readEntity(User.class);

		Assert.assertNull(responseUser.getSalt());
		Assert.assertNull(responseUser.getPassword());

	}

	@Test
	public void shouldClientErrorOnNonUniqueUsername() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String s = gs.serialize(user);

		final Response firstResponse = target("users").request()
				.post(Entity.entity(s, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, firstResponse.getStatus());
		user.setEmail(UserMaker.getNextName() + "@test.test");

		final Response secondResponse = target("users").request()
				.post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, secondResponse.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNonUniqueUsername() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response firstResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, firstResponse.getStatus());
		user.setEmail(UserMaker.getNextName() + "@test.test");
		userString = gs.serialize(user);

		final Response secondResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertTrue(secondResponse.readEntity(String.class).contains("username"));
	}

	@Test
	public void shouldClientErrorOnNonUniqueEmail() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response firstResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, firstResponse.getStatus());
		user.setUsername(UserMaker.getNextName());
		userString = gs.serialize(user);

		final Response secondResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, secondResponse.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNonUniqueEmail() {
		User user = UserMaker.makeMeAUser();
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response firstResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, firstResponse.getStatus());
		user.setUsername(UserMaker.getNextName());
		userString = gs.serialize(user);

		final Response secondResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertTrue(secondResponse.readEntity(String.class).contains("email"));
	}

	@Test
	public void shouldClientErrorOnNullUsername() {
		User user = UserMaker.makeMeAUser();
		user.setUsername(null);
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, response.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNullUsername() {
		User user = UserMaker.makeMeAUser();
		user.setUsername(null);
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertTrue(response.readEntity(String.class).contains("username"));
	}

	@Test
	public void shouldClientErrorOnNullEmail() {
		User user = UserMaker.makeMeAUser();
		user.setEmail(null);
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, response.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNullEmail() {
		User user = UserMaker.makeMeAUser();
		user.setEmail(null);
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertTrue(response.readEntity(String.class).contains("email"));
	}

	@Test
	public void shouldClientErrorOnNullPassword() {
		User user = UserMaker.makeMeAUser();
		user.setPassword(null);

		final Response response = target("users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, response.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNullPassword() {
		User user = UserMaker.makeMeAUser();
		user.setPassword(null);

		final Response response = target("users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		String d = response.readEntity(String.class);
		Assert.assertTrue(d.contains("password"));
	}

	@Test
	public void shouldClientErrorOnNonValidEmail() {
		User user = UserMaker.makeMeAUser();
		user.setEmail("email");
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(400, response.getStatus());
	}

	@Test
	public void shouldReturnUsefulMessageOnNonValidEmail() {
		User user = UserMaker.makeMeAUser();
		user.setEmail("email");
		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response response = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));
		String d = response.readEntity(String.class);
		Assert.assertTrue(d.contains("email"));
	}

}
