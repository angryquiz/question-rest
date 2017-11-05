package com.question.engine.factory.impl.simple.actions.quiz;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;

public class SaveAnswerQuizPositiveAction extends QAPersistenceAwareAction {

	static Logger LOG = Logger.getLogger(SaveAnswerQuizPositiveAction.class);

	@SuppressWarnings("unused")
	protected void doExecute(Object arg) throws Exception {

		ContextDAO ctx = (ContextDAO) arg;

		SessionDAO qASessionDAO = this.getPersistenceService().getSessionData(ctx);

		QuestionDAO dao = null;
		for (QuestionDAO qAQuestionDAO : qASessionDAO.getQuestions()) {
			if (qAQuestionDAO.getQuestionNumber() == ctx.getQuestionSessionNumber()) {
				dao = qAQuestionDAO;
				break;
			}
		}

		LOG.info("ctx.getQuestionAnswer() {} " + ctx.getQuestionAnswer());
		LOG.info("dao.getAnswer() {} " + dao.getAnswer());

		boolean answerIsCorrect = true;

		if (dao.getQuestionType().equals("match-term")) {

			String cleanAnswer = ctx.getQuestionAnswer().replace(" ", "");
			String cleanCorrectAnswer = dao.getAnswer().replace(" ", "");
			if (cleanAnswer.contains(",")) {
				// this is multiple
				Set<String> cleanAnswerSet = new TreeSet<String>(Arrays.asList(cleanAnswer.split(",")));
				Set<String> correctAnswerSet = new TreeSet<String>(Arrays.asList(cleanCorrectAnswer.split(",")));

				String[] cleanAnswerAr = cleanAnswerSet.toArray(new String[cleanAnswerSet.size()]);
				String[] correctAnswerAr = correctAnswerSet.toArray(new String[correctAnswerSet.size()]);

				answerIsCorrect = Arrays.equals(cleanAnswerAr, correctAnswerAr);

			} else {
				// this is single answer. we will just do a comparison
				if (!ctx.getQuestionAnswer().equals(dao.getAnswer().replace(" ", ""))) {
					answerIsCorrect = false;
				}

			}

		} else {
			// answer is same size and matching, i.e a and a and a = a amd
			// length is 1
			if ((ctx.getQuestionAnswer().length() == dao.getAnswer().length())
					&& !ctx.getQuestionAnswer().equals(dao.getAnswer()) && ctx.getQuestionAnswer().length() == 1) {
				answerIsCorrect = false;
			}
			// answer is not same size. a,c and a or a and a,c
			if (!(ctx.getQuestionAnswer().length() == dao.getAnswer().length())) {
				answerIsCorrect = false;
			}

			if ((ctx.getQuestionAnswer().length() == dao.getAnswer().length())
					&& ctx.getQuestionAnswer().length() > 1) {

				Set<String> cleanAnswerSet = new TreeSet<String>(Arrays.asList(ctx.getQuestionAnswer().split(",")));
				Set<String> correctAnswerSet = new TreeSet<String>(Arrays.asList(dao.getAnswer().split(",")));

				String[] cleanAnswerAr = cleanAnswerSet.toArray(new String[cleanAnswerSet.size()]);
				String[] correctAnswerAr = correctAnswerSet.toArray(new String[correctAnswerSet.size()]);

				answerIsCorrect = Arrays.equals(cleanAnswerAr, correctAnswerAr);
			}
		}

		if (dao != null) {

			dao.setCorrect(answerIsCorrect);
			dao.setMemberAnswer(ctx.getQuestionAnswer());

			System.out.println(dao.toString());
			// persist
			this.getPersistenceService().saveData(ctx, qASessionDAO);

		} else {
			throw new RuntimeException("Error occurred! Dao is null.");
		}

	}

}
