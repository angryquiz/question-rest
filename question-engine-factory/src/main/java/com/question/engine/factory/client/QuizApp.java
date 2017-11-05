package com.question.engine.factory.client;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class QuizApp {
	
	private static Logger LOG = Logger.getLogger(QuizApp.class);
	
	public static void main(String[] args) throws Exception {
	    
		Persistence  persistence = PersistenceFactory.getInstance(PersistenceType.REDIS);
		
	    Question question = QuestionFactory.getInstance(QuestionType.SIMPLE, persistence );
	    
	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream is = classloader.getResourceAsStream("excel/sample-match-term.xlsx");

	    List<QuizBucket> questionList = question.getQuizQuestions(is,"12345");
	    
	    for(QuizBucket quizQuestion : questionList) {
	    	LOG.info(quizQuestion.toString());
	    }
	    
	    SaveAnswerResponse saveAnswerResponse = question.saveQuizAnswer("a=a,b=a,c=b,d=d,e=e", questionList.get(0).getMemberNumber(), 
	    		questionList.get(0).getSessionId(), questionList.get(0).getQuestionNumber());

	    LOG.info(saveAnswerResponse.toString());
	    
	    LOG.info("No more question.");

	    List<QuizBucket> result = question.getQuizResult(questionList.get(0).getMemberNumber(), 
	    		questionList.get(0).getSessionId());
	    
	    for(QuizBucket quizQuestion : result ) {
	    	LOG.info(quizQuestion.toString());
	    }	    
		
	}
}
