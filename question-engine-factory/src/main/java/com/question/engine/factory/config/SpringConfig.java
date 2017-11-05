package com.question.engine.factory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.question.engine.factory.impl.simple.actions.CheckAnswerNegativeAction;
import com.question.engine.factory.impl.simple.actions.CheckAnswerPositiveAction;
import com.question.engine.factory.impl.simple.actions.NextQuestionNegativeAction;
import com.question.engine.factory.impl.simple.actions.NextQuestionPositiveAction;
import com.question.engine.factory.impl.simple.actions.StartQANegativeAction;
import com.question.engine.factory.impl.simple.actions.StartQAPositiveAction;
import com.question.engine.factory.impl.simple.actions.WrongAnswerNegativeAction;
import com.question.engine.factory.impl.simple.actions.WrongAnswerPositiveAction;
import com.question.engine.factory.impl.simple.actions.quiz.CheckResultQuizNegativeAction;
import com.question.engine.factory.impl.simple.actions.quiz.CheckResultQuizPositiveAction;
import com.question.engine.factory.impl.simple.actions.quiz.SaveAnswerQuizNegativeAction;
import com.question.engine.factory.impl.simple.actions.quiz.SaveAnswerQuizPositiveAction;
import com.question.engine.factory.impl.simple.actions.quiz.StartQuizNegativeAction;
import com.question.engine.factory.impl.simple.actions.quiz.StartQuizPositiveAction;
import com.question.engine.factory.impl.simple.framework.SpringRuleEngine;
import com.question.engine.factory.impl.simple.rules.CheckAnswerRule;
import com.question.engine.factory.impl.simple.rules.NextQuestionRule;
import com.question.engine.factory.impl.simple.rules.StartQARule;
import com.question.engine.factory.impl.simple.rules.WrongAnswerRule;
import com.question.engine.factory.impl.simple.rules.quiz.CheckResultQuizRule;
import com.question.engine.factory.impl.simple.rules.quiz.SaveAnswerQuizRule;
import com.question.engine.factory.impl.simple.rules.quiz.StartQuizRule;
import com.question.engine.factory.impl.simple.services.SerializedQAPersistence;

@Configuration
public class SpringConfig {
 	
    //------------------------------ check answer quiz rule
    
    @Bean(name="CheckResultQuizPositiveAction")
    public CheckResultQuizPositiveAction checkResultQuizPositiveAction() {
    	CheckResultQuizPositiveAction v = new CheckResultQuizPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckResultQuizNegativeAction")
    public CheckResultQuizNegativeAction checkResultQuizNegativeAction() {
    	CheckResultQuizNegativeAction v = new CheckResultQuizNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckResultQuizRule")
    public CheckResultQuizRule checkResultQuizRule() {
    	CheckResultQuizRule v = new CheckResultQuizRule();
    	v.setNegativeOutcomeStep(checkResultQuizNegativeAction());
    	v.setPositiveOutcomeStep(checkResultQuizPositiveAction());
        return v;
    }
    
    @Bean(name="CheckResultQuizProcessor")
    public SpringRuleEngine checkResultQuizProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(checkResultQuizRule());
        return v;
    }
    
    //------------------------------ save answer quiz rule
    
    @Bean(name="SaveAnswerQuizPositiveAction")
    public SaveAnswerQuizPositiveAction saveAnswerQuizPositiveAction() {
    	SaveAnswerQuizPositiveAction v = new SaveAnswerQuizPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="SaveAnswerQuizNegativeAction")
    public SaveAnswerQuizNegativeAction saveAnswerQuizNegativeAction() {
    	SaveAnswerQuizNegativeAction v = new SaveAnswerQuizNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="SaveAnswerQuizRule")
    public SaveAnswerQuizRule saveAnswerQuizRule() {
    	SaveAnswerQuizRule v = new SaveAnswerQuizRule();
    	v.setNegativeOutcomeStep(saveAnswerQuizNegativeAction());
    	v.setPositiveOutcomeStep(saveAnswerQuizPositiveAction());
        return v;
    }
    
    @Bean(name="SaveAnswerQuizProcessor")
    public SpringRuleEngine saveAnswerQuizProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(saveAnswerQuizRule());
        return v;
    }
	
    //------------------------------ quiz start rule
    
    @Bean(name="StartQuizPositiveAction")
    public StartQuizPositiveAction startQuizPositiveAction() {
    	StartQuizPositiveAction v = new StartQuizPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQuizNegativeAction")
    public StartQuizNegativeAction startQuizNegativeAction() {
    	StartQuizNegativeAction v = new StartQuizNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQuizRule")
    public StartQuizRule startQuizRule() {
    	StartQuizRule v = new StartQuizRule();
    	v.setNegativeOutcomeStep(startQuizNegativeAction());
    	v.setPositiveOutcomeStep(startQuizPositiveAction());
        return v;
    }
    
    @Bean(name="StartQuizProcessor")
    public SpringRuleEngine startQuizProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(startQuizRule());
        return v;
    }
 	
	
    //------------------------------ persistence

    @Bean(name="QAPersistenceService")
    public SerializedQAPersistence qaPersistenceService() {
    	SerializedQAPersistence v = new SerializedQAPersistence();
        return v;
    }

    //------------------------------ wrong answer rules
    
    @Bean(name="WrongAnswerPositiveAction")
    public WrongAnswerPositiveAction wrongAnswerPositiveAction() {
    	WrongAnswerPositiveAction v = new WrongAnswerPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="WrongAnswerNegativeAction")
    public WrongAnswerNegativeAction wrongAnswerNegativeAction() {
    	WrongAnswerNegativeAction v = new WrongAnswerNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="WrongAnswerRule")
    public WrongAnswerRule wrongAnswerRule() {
    	WrongAnswerRule v = new WrongAnswerRule();
    	v.setNegativeOutcomeStep(wrongAnswerNegativeAction());
    	v.setPositiveOutcomeStep(wrongAnswerPositiveAction());
        return v;
    }
    
    @Bean(name="WrongAnswerProcessor")
    public SpringRuleEngine wrongAnswerProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(wrongAnswerRule());
        return v;
    }
    
    //------------------------------ check answer rules
    
    @Bean(name="CheckAnswerPositiveAction")
    public CheckAnswerPositiveAction checkAnswerPositiveAction() {
    	CheckAnswerPositiveAction v = new CheckAnswerPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckAnswerNegativeAction")
    public CheckAnswerNegativeAction checkAnswerNegativeAction() {
    	CheckAnswerNegativeAction v = new CheckAnswerNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="CheckAnswerRule")
    public CheckAnswerRule checkAnswerRule() {
    	CheckAnswerRule v = new CheckAnswerRule();
    	v.setNegativeOutcomeStep(checkAnswerNegativeAction());
    	v.setPositiveOutcomeStep(checkAnswerPositiveAction());
        return v;
    }
    
    @Bean(name="CheckAnswerProcessor")
    public SpringRuleEngine checkAnswerProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(checkAnswerRule());
        return v;
    }
    
    //------------------------------ next question rules
    
    @Bean(name="NextQuestionPositiveAction")
    public NextQuestionPositiveAction nextQuestionPositiveAction() {
    	NextQuestionPositiveAction v = new NextQuestionPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="NextQuestionNegativeAction")
    public NextQuestionNegativeAction nextQuestionNegativeAction() {
    	NextQuestionNegativeAction v = new NextQuestionNegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="NextQuestionRule")
    public NextQuestionRule nextQuestionRule() {
    	NextQuestionRule v = new NextQuestionRule();
    	v.setNegativeOutcomeStep(nextQuestionNegativeAction());
    	v.setPositiveOutcomeStep(nextQuestionPositiveAction());
        return v;
    }
    
    @Bean(name="NextQuestionProcessor")
    public SpringRuleEngine nextQuestionProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(nextQuestionRule());
        return v;
    }
    
    //------------------------------ inital rules
    
    @Bean(name="StartQAPositiveAction")
    public StartQAPositiveAction startQAPositiveAction() {
    	StartQAPositiveAction v = new StartQAPositiveAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQANegativeAction")
    public StartQANegativeAction startQANegativeAction() {
    	StartQANegativeAction v = new StartQANegativeAction();
    	v.setPersistenceService(qaPersistenceService());
        return v;
    }
    
    @Bean(name="StartQARule")
    public StartQARule startQARule() {
    	StartQARule v = new StartQARule();
    	v.setNegativeOutcomeStep(startQANegativeAction());
    	v.setPositiveOutcomeStep(startQAPositiveAction());
        return v;
    }
    
    @Bean(name="StartQAProcessor")
    public SpringRuleEngine startQAProcessor() {
    	SpringRuleEngine v = new SpringRuleEngine();
    	v.setFirstStep(startQARule());
        return v;
    }
   
}