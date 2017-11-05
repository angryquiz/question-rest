package com.question.engine.factory.impl.simple.data;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.persistence.factory.spi.Persistence;

public class SerializedDataHandler extends DataHandler {

	public boolean saveData(ContextDAO ctx, SessionDAO dao) {		
		Persistence p = ctx.getPersistence();
		boolean ret = p.saveData(ctx.getMemberNumber(), ctx.getSessiondId(), dao);
		return ret;
	}
	
	public boolean saveSessionData(ContextDAO ctx) {
		SessionDAO dao = ctx.getQandaSessionDAO();
		
		saveData(ctx, dao);

		return true;
	}


	public SessionDAO getSessionData(ContextDAO ctx) throws Exception {
		
		return (SessionDAO) ctx.getPersistence().retrieveData(ctx.getMemberNumber(), ctx.getSessiondId());
	}

	@Override
	public Object loadData(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
