package com.question.persistence.factory.spi;

import org.apache.log4j.Logger;

public class MySQLPersistence extends Persistence {
	 
	private static Logger LOG = Logger.getLogger(MySQLPersistence.class);
	
	MySQLPersistence() {
        super(PersistenceType.FILE);
        construct();
    }
 
    @Override
    protected void construct() {
    	LOG.info("Building mysql persistence");
    }

	@Override
	public boolean saveData(String memberNumber, String sessionId, Object obj) {
		return false;
		
	}

	@Override
	public Object retrieveData(String memberNumber, String sessionId) {
		return null;
	}
}