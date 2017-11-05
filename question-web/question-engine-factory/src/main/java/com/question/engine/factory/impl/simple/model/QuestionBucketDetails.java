package com.question.engine.factory.impl.simple.model;

public class QuestionBucketDetails {
	
	private int questionNumber;
	private int totalQuestionRunningValue;
	private int totalQuestion;
	private int numberOfSetsDone;
	private int questionSetRunningValue;
	private int questionSetTotalValue;
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public int getTotalQuestionRunningValue() {
		return totalQuestionRunningValue;
	}
	public void setTotalQuestionRunningValue(int totalQuestionRunningValue) {
		this.totalQuestionRunningValue = totalQuestionRunningValue;
	}
	public int getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public int getNumberOfSetsDone() {
		return numberOfSetsDone;
	}
	public void setNumberOfSetsDone(int numberOfSetsDone) {
		this.numberOfSetsDone = numberOfSetsDone;
	}
	public int getQuestionSetRunningValue() {
		return questionSetRunningValue;
	}
	public void setQuestionSetRunningValue(int questionSetRunningValue) {
		this.questionSetRunningValue = questionSetRunningValue;
	}
	public int getQuestionSetTotalValue() {
		return questionSetTotalValue;
	}
	public void setQuestionSetTotalValue(int questionSetTotalValue) {
		this.questionSetTotalValue = questionSetTotalValue;
	}
	
	@Override
	public String toString() {
		return "QuestionBucketDetails [questionNumber=" + questionNumber
				+ ", totalQuestionRunningValue=" + totalQuestionRunningValue
				+ ", totalQuestion=" + totalQuestion + ", numberOfSetsDone="
				+ numberOfSetsDone + ", questionSetRunningValue="
				+ questionSetRunningValue + ", questionSetTotalValue="
				+ questionSetTotalValue + "]";
	}
	
}
