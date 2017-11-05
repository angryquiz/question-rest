package com.question.engine.factory.impl.simple.services;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.data.DataHandler;


public class SerializedQAPersistence implements QAPersistenceInterface {

	public void recordApproval(ContextDAO application) throws Exception {
	}

	public void recordRejection(ContextDAO application) throws Exception {
	}

	public void recordIncomplete(ContextDAO application) throws Exception {
	}

	@Override
	public void saveSessionData(ContextDAO application) throws Exception {
		DataHandler handler = DataHandler.getInstance();
		handler.saveSessionData(application);
		
	}

	@Override
	public SessionDAO getSessionData(ContextDAO application) throws Exception {
		DataHandler handler = DataHandler.getInstance();
		SessionDAO qASessionDAO = handler.getSessionData(application);
		return qASessionDAO;
		
	}

	@Override
	public boolean saveData(ContextDAO ctx, SessionDAO dao)
			throws Exception {
		DataHandler handler = DataHandler.getInstance();
		return handler.saveData(ctx, dao);
	}

}
