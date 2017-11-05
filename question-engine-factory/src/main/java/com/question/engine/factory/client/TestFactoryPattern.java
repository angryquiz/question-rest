package com.question.engine.factory.client;

import org.apache.log4j.Logger;

import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class TestFactoryPattern {
	
	private static Logger LOG = Logger.getLogger(TestFactoryPattern.class);
	
    public static void main(String[] args) {
    	Persistence persistence = PersistenceFactory.getInstance(PersistenceType.FILE);
    	LOG.info(QuestionFactory.getInstance(QuestionType.SIMPLE, persistence));
    	LOG.info(QuestionFactory.getInstance(QuestionType.ADVANCE, persistence));
    }
}
