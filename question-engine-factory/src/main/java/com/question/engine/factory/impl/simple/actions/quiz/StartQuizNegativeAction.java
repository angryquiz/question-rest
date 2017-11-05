package com.question.engine.factory.impl.simple.actions.quiz;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;

public class StartQuizNegativeAction extends QAPersistenceAwareAction {

	protected void doExecute(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		if(ContextDAO.INSUFFICIENT_DATA.equals(application.getStatus()))
			this.getPersistenceService().recordIncomplete(application);
		else
			this.getPersistenceService().recordRejection(application);
	}
}
