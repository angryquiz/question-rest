package com.question.services.factory.spi;

import java.io.File;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class JerseyRestImpl extends Webservices {

	// http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/

	static Logger LOG = Logger.getLogger(JerseyRestImpl.class);

	private Client client = null;

	JerseyRestImpl() {
		super(WebservicesType.JERSEY_REST_CLIENT);
		construct();
	}

	@Override
	protected void construct() {
		LOG.info("Building JERSEY_REST services");
		try {
			client = Client.create();
		} catch (Exception e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}
	}

	public <T> T put(String url, File fileToUpload, Class<T> clazz) {
		T resp;
		try {
			resp = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error : " + e.getMessage());
		}

		final ClientConfig config = new DefaultClientConfig();
		final Client putClient = Client.create(config);

		// https://crispcode.wordpress.com/2012/07/10/java-client-to-upload-files-on-jersey-rest-web-service/

		WebResource webResource = putClient.resource(url);

		final FormDataMultiPart multiPart = new FormDataMultiPart();
		if (fileToUpload != null) {
			multiPart
					.bodyPart(new FileDataBodyPart("inputFile", fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE));
		}

		resp = (T) webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).put(clazz, multiPart);

		return resp;
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

		WebResource webResource = client.resource(url);

		resp = (T) webResource.accept(MediaType.APPLICATION_JSON).get(clazz);

		return resp;
	}

	@Override
	public <T> T get(String baseUrl, String pathUrl, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}