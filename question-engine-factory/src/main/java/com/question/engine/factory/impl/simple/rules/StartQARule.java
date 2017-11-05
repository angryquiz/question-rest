package com.question.engine.factory.impl.simple.rules;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.framework.AbstractRule;


public class StartQARule extends AbstractRule {

	protected boolean makeDecision(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		
		return true;						
	}

}
