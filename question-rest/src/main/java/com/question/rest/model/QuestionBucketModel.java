package com.question.rest.model;

import java.util.Hashtable;
import java.util.Map;

import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QuestionBucketModel {
	
	@ApiModelProperty(position = 1, required = true, value = "Question")
	private String question;
	
	@ApiModelProperty(position = 2, required = true, value = "Selection")
	private Map<String, String> selection;
	
	@ApiModelProperty(position = 3, required = true, value = "Question number")
	private int questionNumber;
	
	@ApiModelProperty(position = 4, required = true, value = "Session Id")
	private String sessionId;
	
	@ApiModelProperty(position = 5, required = true, value = "Answer")
	private String answer;
	
	@ApiModelProperty(position = 6, required = true, value = "Explanation")
	private String explanation;
	
	@ApiModelProperty(position = 7, required = true, value = "Question Bucket Details")
	private QuestionBucketDetailsModel questionBucketDetails;
	
	@ApiModelProperty(position = 8, required = true, value = "Status")
	private QuestionBucketStatus status;

	@ApiModelProperty(position = 9, required = true, value = "Question Type")
	private String questionType;
	
	@ApiModelProperty(position = 10, required = true, value = "questionsMap")
	private Hashtable<String, String> questionsMap;
	
	@ApiModelProperty(position = 11, required = true, value = "answersMap")
	private Hashtable<String, String> answersMap;
	
	
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
	

	
	public QuestionBucketDetailsModel getQuestionBucketDetails() {
		return questionBucketDetails;
	}
	public void setQuestionBucketDetails(QuestionBucketDetailsModel questionBucketDetails) {
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
		return "QuestionBucketModel [question=" + question + ", selection="
				+ selection + ", questionNumber=" + questionNumber
				+ ", sessionId=" + sessionId + ", answer=" + answer
				+ ", explanation=" + explanation + ", questionBucketDetails="
				+ questionBucketDetails + ", status=" + status
				+ ", questionType=" + questionType + "]";
	}


	
}
