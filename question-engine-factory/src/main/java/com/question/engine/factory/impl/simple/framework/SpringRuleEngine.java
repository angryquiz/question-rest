package com.question.engine.factory.impl.simple.framework;

public class SpringRuleEngine {
	
	private AbstractComponent firstStep;

	
	public void setFirstStep(AbstractComponent firstStep) {
		this.firstStep = firstStep;
	}

	public AbstractComponent getFirstStep() {
		return firstStep;
	}
	
	public void processRequest(Object arg) throws Exception {
		firstStep.execute(arg);
	}
	

}
