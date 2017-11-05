package com.question.engine.factory.spi;

import java.util.List;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.persistence.factory.spi.Persistence;
public abstract class Question {
	
    public Question(QuestionType type, Persistence persistence) {
        this.type = type;
        this.persistence = persistence;
        initialize();
    }
 
    private void initialize() {
        // Do one time processing here
    }
 
    // Do subclass level processing in this method
    protected abstract void construct();
 
    private QuestionType type = null;
    private Persistence persistence = null;
    
    
    public Persistence getPersistence() {
		return persistence;
	}

	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}

	public QuestionType getType() {
        return type;
    }
 
    public void setType(QuestionType type) {
        this.type = type;
    }
    
    public abstract <T> QuestionBucket getFirstQuestion(T input, String memberNumber) throws Exception;
    
    public abstract AnswerBucket checkAnswer(String answer, String memberNumber, String sessionId, int questionNumber) throws Exception;
    
    public abstract QuestionBucket getNextQuestion(String memberNumber, String sessionId) throws Exception;
    
    public abstract QuestionBucket getWrongAnswer(String memberNumber, String sessionId) throws Exception;
    
    public abstract <T> List<QuizBucket> getQuizQuestions(T input, String memberNumber) throws Exception;
    
	public abstract SaveAnswerResponse saveQuizAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) throws Exception;

	public abstract List<QuizBucket> getQuizResult(String memberNumber, String sessionId) throws Exception;

	public abstract <T> QuestionBucket getOneQuestion(T input, String memberNumber) throws Exception;
	
}