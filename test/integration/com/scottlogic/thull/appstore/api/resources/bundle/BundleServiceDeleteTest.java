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
import com.scottlogic.thull.appstore.api.resources.BundleService;

public class BundleServiceDeleteTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(BundleService.class);
    }
	
	/**
	 * Test delete
	 * @throws URISyntaxException 
	 */
	@Test
	public void shouldReturnNoConentOnDelete() throws URISyntaxException{
		Bundle bundle = new Bundle();
		bundle.setTitle("test return title");
		final Response createResponse = target("bundle").request().post(Entity.entity(bundle, MediaType.APPLICATION_JSON_TYPE));
		String location = createResponse.getHeaderString("location");
		location = new URI(location).getPath();
		bundle = createResponse.readEntity(Bundle.class);
		
		final Response deleteResponse = target(location).request().delete();
		
		Assert.assertEquals(204, deleteResponse.getStatus());
	}
	
	@Test
	public void shouldReturnNotFoundWhenNotFound() {
		final Response response = target("bundle/notanID").request().delete();
		
		Assert.assertEquals(404, response.getStatus());
	}
	
	
	
	
 

}
