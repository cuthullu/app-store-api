package integration.com.scottlogic.thull.appstore.api.resources.bundle;

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



public class BundleServiceCreateTest extends JerseyTest {

	@Override
    protected Application configure() {
		ResourceConfig testApp = new ResourceConfig(BundleService.class);
        testApp.register(new ConstraintViolationMapper());
        return testApp;
    }
	
	/**
	 * Creation tests
	 */
	@Test
	public void testShouldCreateObjectOkAndLocation() {
		Bundle bundle = new Bundle();
		bundle.setTitle("test title");
		final Response response = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, response.getStatus());
		Assert.assertNotNull(response.getHeaderString("location"));	
	}
	
	@Test
	public void testShouldCreateReturnResourceRepresentation() {
		Bundle bundle = new Bundle();
		bundle.setTitle("test return title");
		final Response response = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));

		Assert.assertEquals(201, response.getStatus());
		Assert.assertEquals(bundle.getTitle(), response.readEntity(Bundle.class).getTitle());	
	}
	
	@Test
	public void testShoudlErrorOnMissingTitle() {
		Bundle bundle = new Bundle();
		final Response response = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testShoudlErrorWithUsefulMessageOnMissingTitle() {
		Bundle bundle = new Bundle();
		final Response response = target("bundle").request()
				.post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		
		Assert.assertTrue(response.readEntity(String.class).contains("title"));
	}
}
