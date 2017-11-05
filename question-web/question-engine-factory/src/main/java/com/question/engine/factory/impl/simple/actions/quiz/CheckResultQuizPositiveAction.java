package com.question.engine.factory.impl.simple.actions.quiz;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;

public class CheckResultQuizPositiveAction extends QAPersistenceAwareAction {

	static Logger LOG = Logger.getLogger(CheckResultQuizPositiveAction.class);

	protected void doExecute(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		SessionDAO session = this.getPersistenceService().getSessionData(application);
		application.setQandaSessionDAO(session);
		LOG.info("Quiz loaded from persistence.");
	}

}
