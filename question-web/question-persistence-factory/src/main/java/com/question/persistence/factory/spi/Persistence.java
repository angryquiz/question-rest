package com.question.persistence.factory.spi;

public abstract class Persistence {

	public Persistence(PersistenceType type) {
		this.type = type;
		initialize();
	}

	private void initialize() {
	}

	// Do subclass level processing in this method
	protected abstract void construct();

	private PersistenceType type = null;

	public PersistenceType getType() {
		return type;
	}

	public void setType(PersistenceType type) {
		this.type = type;
	}

	public abstract boolean saveData(String memberNumber, String sessionId, Object obj);

	public abstract Object retrieveData(String memberNumber, String sessionId);

}