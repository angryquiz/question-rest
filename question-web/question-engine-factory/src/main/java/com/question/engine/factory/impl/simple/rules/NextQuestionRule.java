package com.question.engine.factory.impl.simple.rules;


import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareRule;


public class NextQuestionRule extends QAPersistenceAwareRule {

	protected boolean makeDecision(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		
		
		
		return true;						
	}

}
