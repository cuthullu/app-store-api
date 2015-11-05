package integration.com.scottlogic.thull.appstore.api.resources.user;

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

import com.scottlogic.thull.appstore.api.domains.User;
import com.scottlogic.thull.appstore.api.exception.mapper.ConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.exception.mapper.OtherConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.resources.UserService;

public class UserServiceCreateTest extends JerseyTest{
	
	@Override
    protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(UserService.class);
        testApp.register(new ConstraintViolationMapper());
        testApp.register(new OtherConstraintViolationMapper());
        return testApp;
    }
	
	static int uniqueCounter = 0;
	
	private String getNextName() {
		String next = "test" + uniqueCounter;
		uniqueCounter++;
		return next;
	}
	
	/**
	 * Creation tests
	 */
	@Test
	public void shouldCreateObjectOkAndLocation() {
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");	
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, response.getStatus());
		Assert.assertNotNull(response.getHeaderString("location"));	
	}
	
	@Test
	public void shouldReturnCreatedObject() {
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		User responseUser = response.readEntity(User.class);
		
		Assert.assertEquals(user.getEmail(), responseUser.getEmail());
		Assert.assertEquals(user.getUsername(), responseUser.getUsername());	
	}
	
	@Test
	public void shouldNotReturnHashOrSaltFromCreatedObject() {
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		User responseUser = response.readEntity(User.class);
		
		Assert.assertNull(responseUser.getSalt());
		Assert.assertNull(responseUser.getPassword());	
		
	}
	
	@Test
	public void shouldClientErrorOnNonUniqueUsername(){
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response firstResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(201, firstResponse.getStatus());
		user.setEmail(getNextName() + "@test.test");
		
		final Response secondResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, secondResponse.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNonUniqueUsername(){
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response firstResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(201, firstResponse.getStatus());
		user.setEmail(getNextName() + "@test.test");
		
		final Response secondResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(secondResponse.readEntity(String.class).contains("username"));
	}
	
	@Test
	public void shouldClientErrorOnNonUniqueEmail(){
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response firstResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(201, firstResponse.getStatus());
		user.setUsername(getNextName());
		
		final Response secondResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, secondResponse.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNonUniqueEmail(){
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response firstResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(201, firstResponse.getStatus());
		user.setUsername(getNextName());
		
		final Response secondResponse = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(secondResponse.readEntity(String.class).contains("email"));
	}
	
	@Test
	public void shouldClientErrorOnNullUsername(){
		User user = new User();
		user.setEmail( getNextName() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNullUsername(){
		User user = new User();
		user.setEmail( getNextName() + "@test.test");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(response.readEntity(String.class).contains("username"));
	}
	
	@Test
	public void shouldClientErrorOnNullEmail(){
		User user = new User();
		user.setUsername( getNextName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNullEmail(){
		User user = new User();
		user.setUsername( getNextName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(response.readEntity(String.class).contains("email"));
	}
	
	@Test
	public void shouldClientErrorOnNullPassword(){
		User user = new User();
		user.setUsername( getNextName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNullPassword(){
		User user = new User();
		user.setUsername( getNextName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		String  d = response.readEntity(String.class);
		Assert.assertTrue(d.contains("password"));
	}
	
	@Test
	public void shouldClientErrorOnNonValidEmail(){
		User user = new User();
		user.setUsername( getNextName());
		user.setEmail("email");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void shouldReturnUsefulMessageOnNonValidEmail(){
		User user = new User();
		user.setUsername( getNextName());
		user.setEmail("email");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("password", "password");
		
		final Response response = target("users").request()
				.post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE));
		String  d = response.readEntity(String.class);
		Assert.assertTrue(d.contains("email"));
	}
	
}
