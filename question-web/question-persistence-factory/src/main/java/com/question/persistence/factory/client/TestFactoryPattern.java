package com.question.persistence.factory.client;

import org.apache.log4j.Logger;

import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class TestFactoryPattern {
	
	private static Logger LOG = Logger.getLogger(TestFactoryPattern.class);
	
    public static void main(String[] args) {
    	LOG.info(PersistenceFactory.getInstance(PersistenceType.FILE));
    	LOG.info(PersistenceFactory.getInstance(PersistenceType.MYSQL));

    }
}
