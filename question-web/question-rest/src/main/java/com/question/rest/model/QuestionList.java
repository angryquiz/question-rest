package com.question.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class QuestionList {

    private List<QuestionDAO> questions = new ArrayList<QuestionDAO>();

    public QuestionList() {
    }

    public QuestionList(final Collection<QuestionDAO> questions) {
        this.questions.addAll(questions);
    }

    public List<QuestionDAO> getQuestions() {
        return questions;
    }

}
