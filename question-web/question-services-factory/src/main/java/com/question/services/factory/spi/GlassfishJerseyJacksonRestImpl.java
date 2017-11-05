package com.question.services.factory.spi;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;


//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//

public class GlassfishJerseyJacksonRestImpl extends Webservices {

	// http://www.hascode.com/2013/12/jax-rs-2-0-rest-client-features-by-example/

	private Client client  = null;

	GlassfishJerseyJacksonRestImpl() {
		super(WebservicesType.GLASSSFISH_JERSEY_JACKSON_REST_CLIENT);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building GLASSSFISH_JERSEY_JACKSON_REST_CLIENT services");
		try {
			client = ClientBuilder.newClient(new ClientConfig().register(JacksonFeature.class));
			client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
			client.property(ClientProperties.READ_TIMEOUT, 10000);	
		} catch (Exception e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}
	}

	public <T> T get(String baseUrl, String pathUrl, Class<T> clazz) {		
		T resp;
		try {
			resp = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}	
		WebTarget webTarget = client.target(baseUrl);	
		resp = (T)webTarget.path(pathUrl)
				.request(MediaType.APPLICATION_JSON).get(clazz);	
		//resp = (T)res.readEntity(clazz);
		return resp;		
	}

	@Override
	public <T> T get(String url, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T put(String url, File fileToUpload, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}
	

		
}