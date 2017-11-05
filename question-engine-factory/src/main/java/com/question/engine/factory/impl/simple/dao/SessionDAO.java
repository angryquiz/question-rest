package com.question.engine.factory.impl.simple.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SessionDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String memberNumber;
	private String examCode;
	private Date startDate;
	private Date endDate;	
	private int questionSessionNumber;	
	private List<QuestionDAO> questions;

	private LinkedList<Integer> questionSet;
	private int questionSetRunningValue;
	private int questionSetTotalValue;
	
	private int totalQuestion;
	private int totalQuestionRunningValue;
	private int numberOfSetsDone;
	
	private LinkedList<Integer> wrongAnswers;
	private int wrongAnswersRunningValue;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getWrongAnswersRunningValue() {
		return wrongAnswersRunningValue;
	}

	public void setWrongAnswersRunningValue(int wrongAnswersRunningValue) {
		this.wrongAnswersRunningValue = wrongAnswersRunningValue;
	}

	public LinkedList<Integer> getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(LinkedList<Integer> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public List<QuestionDAO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDAO> questions) {
		this.questions = questions;
	}

	public int getNumberOfSetsDone() {
		return numberOfSetsDone;
	}

	public void setNumberOfSetsDone(int numberOfSetsDone) {
		this.numberOfSetsDone = numberOfSetsDone;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public int getTotalQuestionRunningValue() {
		return totalQuestionRunningValue;
	}

	public void setTotalQuestionRunningValue(int totalQuestionRunningValue) {
		this.totalQuestionRunningValue = totalQuestionRunningValue;
	}

	public LinkedList<Integer> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(LinkedList<Integer> questionSet) {
		this.questionSet = questionSet;
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


	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getQuestionSessionNumber() {
		return questionSessionNumber;
	}
	public void setQuestionSessionNumber(int questionSessionNumber) {
		this.questionSessionNumber = questionSessionNumber;
	}	
}
