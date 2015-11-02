package integration.com.scottlogic.thull.appstore.api.resources;

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
import com.scottlogic.thull.appstore.api.resources.BundleService;



public class BundleServiceReadTest extends JerseyTest {

	@Override
    protected Application configure() {
        return new ResourceConfig(BundleService.class);
    }
 
	/**
	 * Reading tests
	 */
	@Test
	public void testShouldReturnList() {
		final Response response = target("bundle").request().get();
		Bundle[] bundles= response.readEntity(Bundle[].class);
		Assert.assertTrue(bundles.length > 0);
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testShouldGetBundleFromLocation() throws URISyntaxException {
		Bundle bundle = new Bundle();
		bundle.setTitle("test location title");
		final Response createResponse = target("bundle").request().post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle = createResponse.readEntity(Bundle.class);
		
		final Response getResponse = target(location).request().get();
		Bundle gottenBundle = getResponse.readEntity(Bundle.class);
		
		
		Assert.assertEquals(200, getResponse.getStatus());
		Assert.assertEquals(bundle.getId(), gottenBundle.getId());
	}
	
	@Test
	public void testShouldReturnNotFoundForNoneExistent() {
		final Response response = target("bundle/notaid").request().get();
		Assert.assertEquals(404, response.getStatus());
	}

	/**
	 * Deletion tests
	 */
}
