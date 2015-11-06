package integration.com.scottlogic.thull.appstore.api.resources.user;

import java.net.URI;
import java.net.URISyntaxException;

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
import com.scottlogic.thull.appstore.api.resources.UserService;

public class UserServiceDeleteTest extends JerseyTest{

	@Override
    protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(UserService.class);
        return testApp;
    }
	
	@Test
	public void shouldReturnNoContentOnDelete() throws URISyntaxException {
		User user = UserMaker.makeMeAUser();

		Genson gs = new GensonBuilder().include("password").create();
		String userString = gs.serialize(user);

		final Response createResponse = target("users").request()
				.post(Entity.entity(userString, MediaType.APPLICATION_JSON_TYPE));

		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		user = createResponse.readEntity(User.class);
		final Response response = target(location).request().delete();

		
		Assert.assertEquals(204, response.getStatus());

	}
	
	@Test
	public void shouldReturnNotFoundForNonExistantUser() {
		final Response response = target("users/noAnId").request().delete();
		
		Assert.assertEquals(404, response.getStatus());
	}
}
