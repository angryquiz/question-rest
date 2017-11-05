package com.question.engine.factory.impl.simple.rules.quiz;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.framework.AbstractRule;


public class StartQuizRule extends AbstractRule {

	protected boolean makeDecision(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		
		return true;						
	}

}
