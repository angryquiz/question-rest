package com.question.rest.model;

import java.util.Hashtable;
import java.util.Map;

import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QuizBucketModel {
	
	@ApiModelProperty(position = 1, required = true, value = "question")
	private String question;
	
	@ApiModelProperty(position = 2, required = true, value = "selection")
	private Map<String, String> selection;
	
	@ApiModelProperty(position = 3, required = true, value = "questionNumber")
	private int questionNumber;

	@ApiModelProperty(position = 4, required = true, value = "answer")
	private String answer;
	
	@ApiModelProperty(position = 5, required = true, value = "explanation")
	private String explanation;
	
	@ApiModelProperty(position = 6, required = true, value = "questionType")
	private String questionType;

	@ApiModelProperty(position = 7, required = true, value = "memberAnswer")
	private String memberAnswer;

	@ApiModelProperty(position = 8, required = true, value = "correct")
	private boolean correct;

	@ApiModelProperty(position = 9, required = true, value = "memberNumber")
	private String memberNumber;
	
	@ApiModelProperty(position = 10, required = true, value = "sessionId")
	private String sessionId;
	
	@ApiModelProperty(position = 11, required = true, value = "status")
	private QuestionBucketStatus status;
	
	@ApiModelProperty(position = 12, required = true, value = "questionsMap")
	private Hashtable<String, String> questionsMap;
	
	@ApiModelProperty(position = 13, required = true, value = "answersMap")
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
		return "QuizBucketModel [question=" + question + ", selection=" + selection + ", questionNumber="
				+ questionNumber + ", answer=" + answer + ", explanation=" + explanation + ", questionType="
				+ questionType + ", memberAnswer=" + memberAnswer + ", correct=" + correct + ", memberNumber="
				+ memberNumber + ", sessionId=" + sessionId + ", status=" + status + ", questionsMap=" + questionsMap
				+ ", answersMap=" + answersMap + "]";
	}

}
