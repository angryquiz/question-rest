package com.question.engine.factory.impl.simple.actions;

import java.util.List;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.constants.Constants;
import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;
import com.question.engine.factory.impl.simple.utils.QAUtils;

public class NextQuestionPositiveAction extends QAPersistenceAwareAction {

	static Logger LOG = Logger.getLogger(NextQuestionPositiveAction.class);

	protected void doExecute(Object arg) throws Exception {

		ContextDAO application = (ContextDAO) arg;
		SessionDAO session = this.getPersistenceService().getSessionData(application);

		if (session.getStatus() != null && session.getStatus().equals(Constants.NO_MORE_WRONG_ANSWERS_TO_REVIEW)) {
			// re-init wrong answer variables
			session.setWrongAnswers(null);
			session.setWrongAnswersRunningValue(0);

			// will do this in showAnsers logic. to initialize it there.
			// goto next question set
			// initialize to 1 again
			session.setQuestionSetRunningValue(1);
			session.setNumberOfSetsDone(session.getNumberOfSetsDone() + 1); // increment
																			// sets
																			// done.
			QAUtils.evaluteQuestionSet(session, session.getQuestions(), application);
			// initialize this to null so that it will go back to normal routine
			session.setStatus(null);

		} else {
			LOG.info("getWrongAnswersIndexes: " + session.getWrongAnswers());
			LOG.info("getWrongAnswersRunningValue: " + session.getWrongAnswersRunningValue());
			LOG.info("getQuestionSetRunningValue: " + session.getQuestionSetRunningValue());
			LOG.info("getQuestionSetTotalValue: " + session.getQuestionSetTotalValue());

			List<Integer> questionIndexesLimited = session.getQuestionSet();
			int questionSetRunningValue = session.getQuestionSetRunningValue();
			int questionSetTotalValue = session.getQuestionSetTotalValue();
			application.setStatus(ContextDAO.QUESTION_AVAILABLE);
			// when running value reached total set count, start again
			if (questionSetRunningValue < questionSetTotalValue) {
				// 0 based
				int index = session.getQuestionSetRunningValue();

				LOG.info("questionIndexesLimited=index: " + questionIndexesLimited.toString() + "=" + index);

				try {
					application.setQandaQuestion(session.getQuestions().get(questionIndexesLimited.get(index)));
					session.setQuestionSessionNumber(
							session.getQuestions().get(questionIndexesLimited.get(index)).getQuestionNumber());
					session.setTotalQuestionRunningValue(session.getTotalQuestionRunningValue() + 1);
					session.setQuestionSetRunningValue(session.getQuestionSetRunningValue() + 1);
					// } else {
					// application.setStatus(ContextDAO.NO_QUESTION_REMAINING);
					// }
				} catch (IndexOutOfBoundsException e) {
					LOG.info("Exception caught: " + e.toString());
					// application.setStatus(ContextDAO.NO_QUESTION_REMAINING);
					application.setStatus(ContextDAO.QUESTION_SET_TOTAL_REACHED);
				}

			} else {

				// if question set size is equal to wrong answers size then
				// advise to see the answers
				application.setStatus(ContextDAO.QUESTION_SET_TOTAL_REACHED);
			}
		}
		application.setQandaSessionDAO(session);
		this.getPersistenceService().saveSessionData(application);
	}

}
