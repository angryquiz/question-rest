package com.question.services.factory.spi;

import java.io.File;

import org.apache.log4j.Logger;

public class SoapImpl extends Webservices {

	static Logger LOG = Logger.getLogger(SoapImpl.class);

	SoapImpl() {
		super(WebservicesType.SOAP);
		construct();
	}

	@Override
	protected void construct() {
		LOG.info("Building soap services");
	}

	@Override
	public <T> T get(String url, Class<T> clazz) {
		// TODO Auto-generated method stub
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