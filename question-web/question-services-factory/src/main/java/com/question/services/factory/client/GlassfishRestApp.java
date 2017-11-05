package com.question.services.factory.client;

import org.apache.log4j.Logger;

import com.question.services.factory.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class GlassfishRestApp {

	static Logger LOG = Logger.getLogger(GlassfishRestApp.class);

	public static void main(String[] args) {

		Webservices service = WebservicesFactory.getInstance(WebservicesType.GLASSSFISH_JERSEY_REST_CLIENT);

		String jsonString = service.get("/rest/questions/getFirstQuestion/1", String.class);
		LOG.info(jsonString);

		QuestionBucket questionBucket = service.get("/rest/questions/getFirstQuestion/1", QuestionBucket.class);
		LOG.info(questionBucket.toString());

		String jsonNextString = service.get("/rest/questions/getNextQuestion/1/" + questionBucket.getSessionId(),
				String.class);
		LOG.info(jsonNextString);

	}
}
