package com.question.web.services;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.question.services.factory.spi.Webservices;
import com.question.services.factory.spi.WebservicesFactory;
import com.question.services.factory.spi.WebservicesType;

@Service
public class QuestionService {

	private Webservices service = null;
	
	private String questionRestEndpoint = null;

	private static Logger LOG = Logger.getLogger(QuestionService.class);
	
	@PostConstruct
	public void initIt() throws Exception {
		LOG.info("QuestionService-web Init method after properties are set");

		questionRestEndpoint = System.getProperty("questionRestEndpoint");
		
		if (StringUtils.isEmpty(questionRestEndpoint)) {
			questionRestEndpoint = "http://localhost:8080/question-rest";
		}

		service = WebservicesFactory.getInstance(WebservicesType.JERSEY_REST_CLIENT);

	}

	@PreDestroy
	public void cleanUp() throws Exception {
		LOG.info("QuestionService-web Spring Container is destroy! Customer clean up");
	}

	public <T> T get(String url, Class<T> clazz) {
		return service.get(questionRestEndpoint + url, clazz);
	}

	public <T> T put(String url, File fileToUpload, Class<T> clazz) {
		return service.put(questionRestEndpoint + url, fileToUpload, clazz);
	}

}