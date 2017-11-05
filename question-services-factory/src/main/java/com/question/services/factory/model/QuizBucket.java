package com.question.services.factory.model;

import java.util.Hashtable;
import java.util.Map;

public class QuizBucket {
	
	private String question;
	private Map<String, String> selection;
	private int questionNumber;

	private String answer;
	private String explanation;
	
	private String questionType;

	private String memberAnswer;

	private boolean correct;

	private String memberNumber;
	private String sessionId;
	
	private QuestionBucketStatus status;
	
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
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public String getMemberAnswer() {
		return memberAnswer;
	}
	public void setMemberAnswer(String memberAnswer) {
		this.memberAnswer = memberAnswer;
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
		return "QuizBucket [question=" + question + ", selection=" + selection
				+ ", questionNumber=" + questionNumber + ", answer=" + answer
				+ ", explanation=" + explanation + ", questionType="
				+ questionType + ", memberAnswer=" + memberAnswer
				+ ", correct=" + correct + ", memberNumber=" + memberNumber
				+ ", sessionId=" + sessionId + ", status=" + status + "]";
	}


	
}
