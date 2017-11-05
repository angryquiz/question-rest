package com.question.services.factory.model;

import java.util.Hashtable;
import java.util.Map;

public class QuestionBucket {
	
	private String question;
	private Map<String, String> selection;
	private int questionNumber;
	private String sessionId;
	
	private String answer;
	private String explanation;
	
	private String questionType;
	
	Hashtable<String, String> questionsMap = new Hashtable<String, String>();
	Hashtable<String, String> answersMap = new Hashtable<String, String>();
	
	
	public Hashtable<String, String> getQuestionsMap() {
		return questionsMap;
	}
	public void setQuestionsMap(Hashtable<String, String> questionsMap) {
		this.questionsMap = questionsMap;
	}
	public Hashtable<String, String> getAnswersMap() {
		return answersMap;
	}
	public void setAnswersMap(Hashtable<String, String> answersMap) {
		this.answersMap = answersMap;
	}
	
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	private QuestionBucketDetails questionBucketDetails;
	
	private QuestionBucketStatus status;
	
	public QuestionBucketDetails getQuestionBucketDetails() {
		return questionBucketDetails;
	}
	public void setQuestionBucketDetails(QuestionBucketDetails questionBucketDetails) {
		this.questionBucketDetails = questionBucketDetails;
	}
	public QuestionBucketStatus getStatus() {
		return status;
	}
	public void setStatus(QuestionBucketStatus status) {
		this.status = status;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Map<String, String> getSelection() {
		return selection;
	}
	public void setSelection(Map<String, String> selection) {
		this.selection = selection;
	}
	@Override
	public String toString() {
		return "QuestionBucket [question=" + question + ", selection="
				+ selection + ", questionNumber=" + questionNumber
				+ ", sessionId=" + sessionId + ", answer=" + answer
				+ ", explanation=" + explanation + ", questionType="
				+ questionType + ", questionBucketDetails="
				+ questionBucketDetails + ", status=" + status + "]";
	}


	
}
