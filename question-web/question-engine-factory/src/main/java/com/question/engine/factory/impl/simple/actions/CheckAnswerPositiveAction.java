package com.question.engine.factory.impl.simple.actions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.exception.SimpleException;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;

public class CheckAnswerPositiveAction extends QAPersistenceAwareAction {

	static Logger LOG = Logger.getLogger(CheckAnswerPositiveAction.class);

	protected void doExecute(Object arg) throws Exception {

		ContextDAO ctx = (ContextDAO) arg;

		SessionDAO qASessionDAO = this.getPersistenceService().getSessionData(
				ctx);

		if(qASessionDAO == null) {
			throw new SimpleException("Session is not accessible or is expired!");
		}
		
		QuestionDAO dao = null;
		for (QuestionDAO qAQuestionDAO : qASessionDAO.getQuestions()) {
			if (qAQuestionDAO.getQuestionNumber() == ctx
					.getQuestionSessionNumber()) {
				dao = qAQuestionDAO;
				break;
			}
		}

		LOG.info("ctx.getQuestionAnswer() {} " + ctx.getQuestionAnswer());
		LOG.info("dao.getAnswer() {} " + dao.getAnswer());
		
		boolean answerIsCorrect = true;
		
		if(dao.getQuestionType().equals("match-term")) {
			
			String cleanAnswer = ctx.getQuestionAnswer().replace(" ", "");
			String cleanCorrectAnswer = dao.getAnswer().replace(" ", "");
			if(cleanAnswer.contains(",")) {
				//this is multiple
				Set<String> cleanAnswerSet = new TreeSet<String>(Arrays.asList(cleanAnswer.split(",")));
				Set<String> correctAnswerSet = new TreeSet<String>(Arrays.asList(cleanCorrectAnswer.split(",")));

				String[] cleanAnswerAr = cleanAnswerSet.toArray(new String[cleanAnswerSet.size()]);
				String[] correctAnswerAr = correctAnswerSet.toArray(new String[correctAnswerSet.size()]);
				
				answerIsCorrect = Arrays.equals(cleanAnswerAr, correctAnswerAr);

				
			} else {
				//this is single answer. we will just do a comparison
				if(!ctx.getQuestionAnswer().equals(dao.getAnswer().replace(" ", ""))) {
					answerIsCorrect = false;
				}
				
			}
			
		} else {
			//answer is same size and matching, i.e a and a and a = a amd length is 1
			if( (ctx.getQuestionAnswer().length() == dao.getAnswer().length()) 
					&& !ctx.getQuestionAnswer().equals(dao.getAnswer())
					&& ctx.getQuestionAnswer().length() == 1 ) {
				answerIsCorrect = false;
			}
			//answer is not same size. a,c and a or a and a,c
			if( !(ctx.getQuestionAnswer().length() == dao.getAnswer().length()) ) {
				answerIsCorrect = false;
			}	
			
			if( (ctx.getQuestionAnswer().length() == dao.getAnswer().length()) 
					&& ctx.getQuestionAnswer().length() > 1) {			
				
				Set<String> cleanAnswerSet = new TreeSet<String>(Arrays.asList(ctx.getQuestionAnswer().split(",")));
				Set<String> correctAnswerSet = new TreeSet<String>(Arrays.asList(dao.getAnswer().split(",")));

				String[] cleanAnswerAr = cleanAnswerSet.toArray(new String[cleanAnswerSet.size()]);
				String[] correctAnswerAr = correctAnswerSet.toArray(new String[correctAnswerSet.size()]);
				
				answerIsCorrect = Arrays.equals(cleanAnswerAr, correctAnswerAr);
				
			}			
		}
		

 		
		
		
		if (answerIsCorrect) {
			int correct = dao.getCorrectAnswerCount();
			int c = correct + 1;
			dao.setCorrectAnswerCount(c);
			this.getPersistenceService().saveData(ctx, qASessionDAO);
			LOG.info("getCorrectAnswerCount: "+dao.getCorrectAnswerCount());
			ctx.setStatus(ContextDAO.ANSWER_IS_CORRECT);

		} else {
			// logger.debug(dao.getCorrectAnswerCount());
			int c = 0;
			dao.setCorrectAnswerCount(c);
			this.getPersistenceService().saveData(ctx, qASessionDAO);
			// logger.debug(dao.getCorrectAnswerCount());
			ctx.setStatus(ContextDAO.ANSWER_NOT_CORRECT);

			// if answer not correct, insert the q# to wrongAnswer
			if (qASessionDAO.getWrongAnswers() == null) {
				LinkedList<Integer> wrongAnswers = new LinkedList<Integer>();
				wrongAnswers.add(ctx.getQuestionSessionNumber());
				qASessionDAO.setWrongAnswers(wrongAnswers);
			} else {
				qASessionDAO.getWrongAnswers().add(
						ctx.getQuestionSessionNumber());
			}
			LOG.info("Wrong answers: "
					+ qASessionDAO.getWrongAnswers().toString());
			
			// persist
			this.getPersistenceService().saveData(ctx, qASessionDAO);

		}

	}

}
