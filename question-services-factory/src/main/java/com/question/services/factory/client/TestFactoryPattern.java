package com.question.services.factory.client;

import org.apache.log4j.Logger;

import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class TestFactoryPattern {

	static Logger LOG = Logger.getLogger(TestFactoryPattern.class);

	public static void main(String[] args) {
		LOG.info(WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT));
		LOG.info(WebservicesFactory.getInstance(WebservicesType.GLASSSFISH_JERSEY_REST_CLIENT));
		LOG.info(WebservicesFactory.getInstance(WebservicesType.SOAP));

	}
}
