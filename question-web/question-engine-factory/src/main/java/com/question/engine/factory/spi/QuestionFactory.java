package com.question.engine.factory.spi;

import com.question.persistence.factory.spi.Persistence;

public class QuestionFactory {
    public static Question getInstance(QuestionType type, Persistence persistence) {
        Question question = null;
        switch (type) {
 
        case SIMPLE:
        	question = new SimpleQuestion(persistence);
            break;
 
        case ADVANCE:
        	question = new AdvanceQuestion(persistence);
            break;
 
        default:
            // throw some exception
            break;
        }
        return question;
    }
}