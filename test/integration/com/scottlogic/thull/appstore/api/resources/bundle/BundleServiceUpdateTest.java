package integration.com.scottlogic.thull.appstore.api.resources.bundle;

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

import com.scottlogic.thull.appstore.api.domains.Bundle;
import com.scottlogic.thull.appstore.api.exception.mapper.ConstraintViolationMapper;
import com.scottlogic.thull.appstore.api.resources.BundleService;

public class BundleServiceUpdateTest extends JerseyTest {

	@Override
    protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(BundleService.class);
        testApp.register(new ConstraintViolationMapper());
        return testApp;
    }
	
	@Test
	public void shouldOkOnUpdate() throws URISyntaxException {
		
		Bundle bundle = new Bundle();
		String initialTitle = "test initial title";
		bundle.setTitle(initialTitle);
		final Response createResponse = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle = createResponse.readEntity(Bundle.class);
		
		final Response updatedResponse = target(location).request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(200, updatedResponse.getStatus());
	}
 
	@Test
	public void shouldUpdateABundlesTitle() throws URISyntaxException {
		Bundle bundle = new Bundle();
		bundle.setTitle("test initial title");
		final Response createResponse = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle.setTitle("test updated title");
		
		final Response updatedResponse = target(location).request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Bundle updatedBundle = updatedResponse.readEntity(Bundle.class);
		Assert.assertEquals(bundle.getTitle(), updatedBundle.getTitle());
	}
	
	@Test
	public void shouldNotChangeBundleIdOnUpdate() throws URISyntaxException {
		Bundle bundle = new Bundle();
		bundle.setTitle("test initial title");
		final Response createResponse = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle.setTitle("test updated title");
		
		final Response updatedResponse = target(location).request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Bundle updatedBundle = updatedResponse.readEntity(Bundle.class);
		Assert.assertEquals(bundle.getId(), updatedBundle.getId());
	}
	
	@Test
	public void shouldRespondNotFoundWhenNotFound() {
		Bundle bundle = new Bundle();
		final Response response = target("bundle/notanID").request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(404, response.getStatus());
	}
	
	@Test
	public void shouldRespondClientErrorWhenMissingField() throws URISyntaxException {
		Bundle bundle = new Bundle();
		bundle.setTitle("test update missing title");
		final Response createResponse = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle.setTitle(null);
		

		final Response updateResponse = target(location).request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, updateResponse.getStatus());
	}
	
	@Test
	public void shouldRespondWithUsefulMessageWhenFieldMissing() throws URISyntaxException {
		Bundle bundle = new Bundle();
		bundle.setTitle("test update missing title");
		final Response createResponse = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle.setTitle(null);

		final Response updateResponse = target(location).request()
				.put(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(updateResponse.readEntity(String.class).contains("title"));
	}
}
