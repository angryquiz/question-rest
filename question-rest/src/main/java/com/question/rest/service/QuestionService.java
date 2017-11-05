package com.question.rest.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

@Service
public class QuestionService {

	private Question question = null;
	
	private static Logger LOG = Logger.getLogger(QuestionService.class);
	
	@PostConstruct
	public void initIt() throws Exception {
		LOG.info("Init method after properties are set");

		Persistence persistence = PersistenceFactory
				.getInstance(PersistenceType.REDIS);

		question = QuestionFactory
				.getInstance(QuestionType.SIMPLE, persistence);

	}

	@PreDestroy
	public void cleanUp() throws Exception {
		LOG.info("Spring Container destroy! clean up");
	}
	
    public QuestionBucket getOneQuestion(Object object, String memberNumber) throws Exception {
    	return question.getOneQuestion(object,memberNumber);
    }
    
    public List<QuizBucket> getQuizQuestions(Object object, String memberNumber) throws Exception {
    	List<QuizBucket> questionList = question.getQuizQuestions(object,memberNumber);
    	LOG.info("questionList.sizee() {} "+questionList.size());
    	return questionList;
    }
    
	public SaveAnswerResponse saveQuizAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) throws Exception {
		SaveAnswerResponse saveAnswerResponse = question.saveQuizAnswer(answer, 
				memberNumber, sessionId, questionNumber);
		LOG.info("saveAnswerResponse " + saveAnswerResponse.toString());
		return saveAnswerResponse; 
	}

	public List<QuizBucket> getQuizResult(String memberNumber, String sessionId) throws Exception {
		List<QuizBucket> result = question.getQuizResult(memberNumber, 
				sessionId);
		return result;
	}

	public QuestionBucket getFirstQuestion(Object object, String memberNumber) throws Exception {
		QuestionBucket questionBucket = question.getFirstQuestion(object,
				memberNumber);
		LOG.info(questionBucket.toString());
		LOG.info(questionBucket.getStatus());
		return questionBucket;
	}

	public QuestionBucket getNextQuestion(String memberNumber, String sessionId) throws Exception {
		QuestionBucket questionBucket = question.getNextQuestion(memberNumber,
				sessionId);
		LOG.info(questionBucket.toString());
		LOG.info(questionBucket.getStatus());
		return questionBucket;
	}

	public QuestionBucket getWrongAnswer(String memberNumber, String sessionId) throws Exception {
		QuestionBucket questionBucket = question.getWrongAnswer(memberNumber,
				sessionId);
		LOG.info(questionBucket.toString());
		LOG.info(questionBucket.getStatus());
		return questionBucket;
	}

	public AnswerBucket checkAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) throws Exception {
		AnswerBucket answerBucket = question.checkAnswer(answer, memberNumber,
				sessionId, questionNumber);
		LOG.info(answerBucket.getAnswer());
		return answerBucket;
	}

}