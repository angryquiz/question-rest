package com.question.engine.factory.impl.simple.dao;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.question.persistence.factory.spi.Persistence;

public class ContextDAO {
	
	public static final String INVALID_STATE = "Sorry we are not doing business in your state";
	public static final String INVALID_INCOME_EXPENSE_RATIO = "Sorry we cannot provide the loan given this expense/income ratio";
	public static final String APPROVED = "Your application has been approved";
	public static final String INSUFFICIENT_DATA = "You did not provide enough information on your application";
	public static final String INPROGRESS = "in progress";
	
	public static final String NO_MORE_WRONG_ANSWERS_TO_REVIEW = "No more wrong answer to review. ";
	public static final String WRONG_ANSWER_FOUND = "Wrong answer found. ";
	public static final String QUESTION_SET_TOTAL_REACHED = "Question set total reached. ";
	public static final String ANSWER_IS_CORRECT = "Answer is correct";
	public static final String ANSWER_NOT_CORRECT = "Answer is not correct";
	public static final String COMPLETED_A_SET = "Completed a set";
	public static final String NO_QUESTION_REMAINING = "No more question remaining";
	public static final String QUESTION_AVAILABLE = "Question is available";
	
	public static final String[] STATUSES = 
		new String[] {
			INSUFFICIENT_DATA, INVALID_INCOME_EXPENSE_RATIO, INVALID_STATE, APPROVED, INPROGRESS, 
			ANSWER_IS_CORRECT, ANSWER_NOT_CORRECT, COMPLETED_A_SET, NO_QUESTION_REMAINING, 
			QUESTION_AVAILABLE, QUESTION_SET_TOTAL_REACHED, WRONG_ANSWER_FOUND,
			NO_MORE_WRONG_ANSWERS_TO_REVIEW
		};


	private String status;
	private SessionDAO qandaSessionDAO;
	
	private String memberNumber;
	private String examCode;
	private QuestionDAO qandaQuestion;
	private String sessiondId;
	private int questionSessionNumber;	
	private String questionAnswer;
	
	private String excelFile;
	
	
	private Persistence persistence;
	
	
	private InputStream inputFile;
	
	private List<QuestionDAO> inputQuestionList;
	
	public List<QuestionDAO> getInputQuestionList() {
		return inputQuestionList;
	}
	public void setInputQuestionList(List<QuestionDAO> inputQuestionList) {
		this.inputQuestionList = inputQuestionList;
	}
	public InputStream getInputFile() {
		return inputFile;
	}
	public void setInputFile(InputStream inputFile) {
		this.inputFile = inputFile;
	}
	public Persistence getPersistence() {
		return persistence;
	}
	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}
	public String getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public int getQuestionSessionNumber() {
		return questionSessionNumber;
	}
	public void setQuestionSessionNumber(int questionSessionNumber) {
		this.questionSessionNumber = questionSessionNumber;
	}
	public String getSessiondId() {
		return sessiondId;
	}
	public void setSessiondId(String sessiondId) {
		this.sessiondId = sessiondId;
	}
	public QuestionDAO getQandaQuestion() {
		return qandaQuestion;
	}
	public void setQandaQuestion(QuestionDAO qandaQuestion) {
		this.qandaQuestion = qandaQuestion;
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
	public SessionDAO getQandaSessionDAO() {
		return qandaSessionDAO;
	}
	public void setQandaSessionDAO(SessionDAO qandaSessionDAO) {
		this.qandaSessionDAO = qandaSessionDAO;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		if(!Arrays.asList(STATUSES).contains(status))
			throw new IllegalArgumentException("invalid status:" + status);
		this.status = status;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	
}
