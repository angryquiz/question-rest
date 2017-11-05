package com.question.engine.factory.impl.simple.services;

import com.question.engine.factory.impl.simple.framework.AbstractAction;

public abstract class QAPersistenceAwareAction extends AbstractAction {

	private QAPersistenceInterface persistenceService;

	/**
	 * @param persistenceService The persistenceService to set.
	 */
	public void setPersistenceService(QAPersistenceInterface persistenceService) {
		this.persistenceService = persistenceService;
	}

	/**
	 * @return Returns the persistenceService.
	 */
	public QAPersistenceInterface getPersistenceService() {
		return persistenceService;
	}
}
