package com.question.engine.factory.impl.simple.model;


public class SaveAnswerResponse {
	
	private int code;
	private String description;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SaveAnswerResponse [code=" + code + ", description="
				+ description + "]";
	}
	
	
}
