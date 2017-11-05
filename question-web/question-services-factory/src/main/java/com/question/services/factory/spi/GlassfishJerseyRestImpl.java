package com.question.services.factory.spi;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.question.services.factory.model.QuestionBucket;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//

public class GlassfishJerseyRestImpl extends Webservices {

	// http://www.hascode.com/2013/12/jax-rs-2-0-rest-client-features-by-example/

	private WebTarget webTarget = null;

	GlassfishJerseyRestImpl() {
		super(WebservicesType.GLASSSFISH_JERSEY_REST_CLIENT);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building JERSEY_REST services");
		try {
			Client client = ClientBuilder.newClient(new ClientConfig());
			client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
			client.property(ClientProperties.READ_TIMEOUT, 10000);
			webTarget = client.target("http://localhost:8080/question-rest");			
		} catch (Exception e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}
	}

	public <T> T get(String url, Class<T> clazz) {		
		T resp;
		try {
			resp = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}
		resp = (T)webTarget.path(url)
				.request(MediaType.APPLICATION_JSON).get(clazz);	
		return resp;		
	}
	

	@SuppressWarnings("unchecked")
	public <T> T getNextQuestion(String memberNumber, String sessionId,Class<T> clazz) {
		//String url = "/rest/questions/getFirstQuestion/"+memberNumber;	
		String url = "/rest/questions/getNextQuestion/"+memberNumber+"/"+sessionId;
		if(clazz.equals(String.class)) {
			String resp = webTarget.path(url)
					.request(MediaType.APPLICATION_JSON).get(String.class);		
			return (T) resp;		
		} else if(clazz.equals(QuestionBucket.class)) {
			QuestionBucket resp = webTarget.path(url)
					.request(MediaType.APPLICATION_JSON).get(QuestionBucket.class);	
			return (T) resp;
		}
		return null;
	}

	@Override
	public <T> T get(String baseUrl, String pathUrl, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T put(String url, File fileToUpload, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}
		
}