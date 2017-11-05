package com.question.engine.factory.spi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.question.engine.factory.config.SpringConfig;
import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.framework.SpringRuleEngine;
import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketDetails;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.persistence.factory.spi.Persistence;

public class SimpleQuestion extends Question {

	private static Logger LOG = Logger.getLogger(SimpleQuestion.class);

	private SpringRuleEngine startQengine = null;
	private SpringRuleEngine nextQengine = null;
	private SpringRuleEngine checkAengine = null;
	private SpringRuleEngine wrongEngine = null;

	private SpringRuleEngine startQuizEngine = null;
	private SpringRuleEngine saveAnswerQuizEngine = null;
	private SpringRuleEngine checkResultQuizEngine = null;

	public final static String BLANK = "";

	SimpleQuestion(Persistence persistence) {
		super(QuestionType.SIMPLE, persistence);
		construct();
	}

	@Override
	protected void construct() {

		LOG.info("Building simple question");

		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		// ---------------- quiz logic

		startQuizEngine = (SpringRuleEngine) context.getBean("StartQuizProcessor");
		saveAnswerQuizEngine = (SpringRuleEngine) context.getBean("SaveAnswerQuizProcessor");
		checkResultQuizEngine = (SpringRuleEngine) context.getBean("CheckResultQuizProcessor");

		// ----------------- question logic

		startQengine = (SpringRuleEngine) context.getBean("StartQAProcessor");

		nextQengine = (SpringRuleEngine) context.getBean("NextQuestionProcessor");

		checkAengine = (SpringRuleEngine) context.getBean("CheckAnswerProcessor");

		wrongEngine = (SpringRuleEngine) context.getBean("WrongAnswerProcessor");

	}

	@Override
	public List<QuizBucket> getQuizResult(String memberNumber, String sessionId) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setPersistence(getPersistence());
		checkResultQuizEngine.processRequest(application);
		return applicationToQuizBucketList(application, true);
	}

	@Override
	public SaveAnswerResponse saveQuizAnswer(String answer, String memberNumber, String sessionId, int questionNumber)
			throws Exception {
		ContextDAO application = new ContextDAO();
		application.setPersistence(getPersistence());
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setQuestionSessionNumber(questionNumber);
		application.setQuestionAnswer(answer);

		saveAnswerQuizEngine.processRequest(application);
		SaveAnswerResponse response = new SaveAnswerResponse();
		response.setCode(0);
		response.setDescription("Answer saved.");
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> QuestionBucket getOneQuestion(T input, String memberNumber) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setPersistence(getPersistence());
		if (input instanceof InputStream) {
			InputStream in = (InputStream) input;
			application.setInputFile(in);
		} else if (input instanceof List<?>) {
			List<QuestionDAO> in = (List<QuestionDAO>) input;
			application.setInputQuestionList(in);
		}
		startQengine.processRequest(application);
		return applicationToBucket(application, false);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<QuizBucket> getQuizQuestions(T input, String memberNumber) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setPersistence(getPersistence());
		if (input instanceof InputStream) {
			InputStream in = (InputStream) input;
			application.setInputFile(in);
		} else if (input instanceof List<?>) {
			List<QuestionDAO> in = (List<QuestionDAO>) input;
			application.setInputQuestionList(in);
		}
		
		startQuizEngine.processRequest(application);
		return applicationToQuizBucketList(application, false);
	}

	@Override
	public QuestionBucket getWrongAnswer(String memberNumber, String sessionId) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setPersistence(getPersistence());
		wrongEngine.processRequest(application);
		return applicationToBucket(application, true);
	}

	@Override
	public <T> QuestionBucket getFirstQuestion(T input, String memberNumber) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setPersistence(getPersistence());
		if (input instanceof InputStream) {
			InputStream in = (InputStream) input;
			application.setInputFile(in);
		}
		startQengine.processRequest(application);
		return applicationToBucket(application, false);

	}

	@Override
	public AnswerBucket checkAnswer(String answer, String memberNumber, String sessionId, int questionNumber)
			throws Exception {
		ContextDAO application = new ContextDAO();
		application.setPersistence(getPersistence());
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setQuestionSessionNumber(questionNumber);
		application.setQuestionAnswer(answer);

		checkAengine.processRequest(application);
		AnswerBucket bucket = new AnswerBucket();
		bucket.setAnswer(application.getStatus().toString());
		return bucket;
	}

	@Override
	public QuestionBucket getNextQuestion(String memberNumber, String sessionId) throws Exception {

		ContextDAO application = new ContextDAO();
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setPersistence(getPersistence());
		nextQengine.processRequest(application);
		return applicationToBucket(application, false);

	}

	/**
	 * Converts application to QuizBucket
	 * 
	 * @param application
	 * @return
	 */
	private List<QuizBucket> applicationToQuizBucketList(ContextDAO application, boolean showAnswer) {

		List<QuizBucket> questionListResp = new ArrayList<QuizBucket>();
		List<QuestionDAO> questionList = application.getQandaSessionDAO().getQuestions();
		if (questionList.size() > 0) {
			Collections.shuffle(questionList);
			for (QuestionDAO question : questionList) {
				QuizBucket quizBucket = new QuizBucket();
				quizBucket.setQuestion(question.getQuestion());
				quizBucket.setSelection(question.getSelections());
				quizBucket.setQuestionType(question.getQuestionType());
				quizBucket.setQuestionNumber(question.getQuestionNumber());
				if (showAnswer) {
					quizBucket.setAnswer(question.getAnswer());
					quizBucket.setCorrect(question.isCorrect());
					quizBucket.setMemberAnswer(question.getMemberAnswer());
					quizBucket.setExplanation(question.getExplanation());
					quizBucket.setStatus(QuestionBucketStatus.QUIZ_QUESTION_ANSWERED);
					quizBucket.setMemberAnswer(question.getMemberAnswer() == null ? "" : question.getMemberAnswer());
				} else {
					quizBucket.setAnswer(BLANK);
					quizBucket.setCorrect(false);
					quizBucket.setMemberAnswer(BLANK);
					quizBucket.setExplanation(BLANK);
					quizBucket.setStatus(QuestionBucketStatus.QUIZ_QUESTION_INITIALIZED);
				}
				quizBucket.setSessionId(application.getSessiondId());
				quizBucket.setMemberNumber(application.getMemberNumber());
				if (quizBucket.getQuestionType().equals("match-term")) {
					addQuestionsAndAnswersMap(quizBucket);
				}
				questionListResp.add(quizBucket);
			}
		}
		return questionListResp;
	}

	private void addQuestionsAndAnswersMap(QuizBucket quizBucket) {

		Hashtable<String, String> questionsMap = new Hashtable<String, String>();
		Hashtable<String, String> answersMap = new Hashtable<String, String>();

		TreeMap<String, String> treeMap = new TreeMap<String, String>(quizBucket.getSelection());

		separateQuestionAndAnswersMap(treeMap, questionsMap, answersMap);
		
		quizBucket.setAnswersMap(answersMap);
		quizBucket.setQuestionsMap(questionsMap);

	}
	
	private void addQuestionsAndAnswersMap(QuestionBucket questionBucket) {

		Hashtable<String, String> questionsMap = new Hashtable<String, String>();
		Hashtable<String, String> answersMap = new Hashtable<String, String>();

		TreeMap<String, String> treeMap = new TreeMap<String, String>(questionBucket.getSelection());

		separateQuestionAndAnswersMap(treeMap, questionsMap, answersMap);
		
		questionBucket.setAnswersMap(answersMap);
		questionBucket.setQuestionsMap(questionsMap);

	}	

	private void separateQuestionAndAnswersMap(TreeMap<String, String> treeMap, Hashtable<String, String> questionsMap,
			Hashtable<String, String> answersMap) {

		Iterator<Entry<String, String>> treeMapIt = treeMap.entrySet().iterator();
		boolean showAnsList = false;
		String[] alpha = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		int i = 0;
		while (treeMapIt.hasNext()) {
			Entry<String, String> e1 = treeMapIt.next();
			if (e1.getValue().contains("|")) {
				showAnsList = true;
				LOG.info("Possible answers:");
				i = 0;
				continue;
			}
			LOG.info((showAnsList ? alpha[i] : e1.getKey()) + ". " + e1.getValue());
			if (showAnsList) {
				answersMap.put(alpha[i], e1.getValue());
			} else {
				questionsMap.put(e1.getKey(), e1.getValue());
			}
			i++;
		}
	}

	private QuestionBucket applicationToBucket(ContextDAO application, boolean showAnswer) {
		QuestionDAO qa = application.getQandaQuestion();
		QuestionBucket bucket = new QuestionBucket();
		bucket.setQuestion(qa == null ? "" : qa.getQuestion());
		bucket.setSelection(qa == null ? new HashMap<String, String>() : qa.getSelections());
		bucket.setQuestionNumber(application.getQandaSessionDAO().getQuestionSessionNumber());
		bucket.setSessionId(application.getSessiondId());
		bucket.setQuestionType(qa == null ? "" : qa.getQuestionType());
		if (application.getStatus().equals(ContextDAO.QUESTION_AVAILABLE)) {
			bucket.setStatus(QuestionBucketStatus.QUESTION_AVAILABLE);
		} else if (application.getStatus().equals(ContextDAO.QUESTION_SET_TOTAL_REACHED)) {
			bucket.setStatus(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED);
		} else if (application.getStatus().equals(ContextDAO.WRONG_ANSWER_FOUND)) {
			bucket.setStatus(QuestionBucketStatus.WRONG_ANSWER_FOUND);
		}
		QuestionBucketDetails details = new QuestionBucketDetails();
		details.setQuestionNumber(application.getQandaSessionDAO().getQuestionSessionNumber());
		details.setTotalQuestion((application.getQandaSessionDAO().getTotalQuestionRunningValue() - 1));
		details.setTotalQuestion(application.getQandaSessionDAO().getTotalQuestion());
		details.setNumberOfSetsDone(application.getQandaSessionDAO().getNumberOfSetsDone());
		details.setQuestionSetRunningValue((application.getQandaSessionDAO().getQuestionSetRunningValue() - 1));
		details.setQuestionSetTotalValue(application.getQandaSessionDAO().getQuestionSetTotalValue());
		bucket.setQuestionBucketDetails(details);

		if (showAnswer) {
			bucket.setAnswer(qa == null ? "NA" : qa.getAnswer());
			bucket.setExplanation(qa == null ? "NA" : qa.getExplanation());
		}
		
		if (bucket.getQuestionType().equals("match-term")) {
			addQuestionsAndAnswersMap(bucket);
		}

		return bucket;
	}

}