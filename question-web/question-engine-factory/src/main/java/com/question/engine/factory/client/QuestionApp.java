package com.question.engine.factory.client;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.utils.QAUtils;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class QuestionApp {
	
	private static Logger LOG = Logger.getLogger(QuestionApp.class);
	
	public static void main(String[] args) throws Exception {
	    
		Persistence  persistence = PersistenceFactory.getInstance(PersistenceType.REDIS);
		
	    Question question = QuestionFactory.getInstance(QuestionType.SIMPLE, persistence );
	    
	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream is = classloader.getResourceAsStream("excel/sample-match-term.xlsx");
	    QuestionBucket questionBucket = question.getFirstQuestion(is,"12345");
	    
	    LOG.info(questionBucket.toString());
	    LOG.info(questionBucket.getStatus());
	    
	    String answer = QAUtils.showQuestionHelper(questionBucket, false);
	    LOG.info(answer);
	    
	    AnswerBucket answerBucket = question.checkAnswer(answer, "12345", 
	    		questionBucket.getSessionId(), questionBucket.getQuestionNumber());
	    LOG.info(answerBucket);
	    
	    boolean moreQuestion = true;
	    while(moreQuestion) {
	    	questionBucket = question.getNextQuestion("12345", questionBucket.getSessionId());
		    LOG.info(questionBucket.toString());
	    	LOG.info(questionBucket.getStatus());
	    	
	    	if(questionBucket.getStatus() == null) {
	    		
	    		LOG.info("Done looping of more question.");
	    		moreQuestion = false;
	    		
	    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_AVAILABLE )) {
	    		
	    		answer = QAUtils.showQuestionHelper(questionBucket, false);
			    LOG.info(answer);
			    
			    answerBucket = question.checkAnswer(answer, "12345", 
			    		questionBucket.getSessionId(), questionBucket.getQuestionNumber());
			    LOG.info(answerBucket);
			    
	    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED )) {
	    		
	    		if(LOG.isDebugEnabled()) {
	    			LOG.debug("check for wrong answer list");
	    		}
		    	boolean repeatWrongList = true;
			    while(repeatWrongList) {
			    	questionBucket = question.getWrongAnswer("12345", questionBucket.getSessionId());
				    LOG.info(questionBucket.toString());
			    	LOG.info(questionBucket.getStatus());
			    	
			    	if (questionBucket.getStatus() == null) {
			    		//done looping on answer
			    		LOG.info("Done looping of answer list.");
			    		repeatWrongList = false;
			    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.WRONG_ANSWER_FOUND )) {
			    		QAUtils.showQuestionHelper(questionBucket, true);
					} else {
						LOG.info("Done looping of answer list. Status = " + questionBucket.getStatus());
						repeatWrongList = false;
					}
			    	
			    }
	    	} else {
	    		moreQuestion = false;
				LOG.info(questionBucket.getStatus());
				LOG.info("No more question. Exiting..");
	    	}

	    }
	    
	    LOG.info("No more question.");
	    	
	}
}
