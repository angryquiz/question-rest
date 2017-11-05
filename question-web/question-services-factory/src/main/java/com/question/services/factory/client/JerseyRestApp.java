package com.question.services.factory.client;

import java.io.File;

import org.apache.log4j.Logger;

import com.question.services.factory.model.QuestionBucket;
import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

public class JerseyRestApp {
	
	static Logger LOG = Logger.getLogger(JerseyRestApp.class);

	public static void main(String[] args) {
		
		Webservices service = WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT);
		
		String jsonString = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1", String.class);
		LOG.info(jsonString);
		
		QuestionBucket questionBucket = service.get("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1", QuestionBucket.class);
		LOG.info(questionBucket.toString());

		String jsonNextString = service.get("http://localhost:8080/question-rest/rest/questions/getNextQuestion/1/"+questionBucket.getSessionId(), String.class);
		LOG.info(jsonNextString);
		
		QuestionBucket questionBucket2 = service.put("http://localhost:8080/question-rest/rest/questions/getFirstQuestion/1/inputFile", new File("/src/main/resources/excel/sample2-tab-lx.xlsx"), QuestionBucket.class);
		LOG.info(questionBucket2.toString());
			

	}
}
