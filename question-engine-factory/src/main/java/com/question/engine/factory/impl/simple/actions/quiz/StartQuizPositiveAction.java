package com.question.engine.factory.impl.simple.actions.quiz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.data.DataHandler;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;
import com.question.engine.factory.impl.simple.utils.QAUtils;


public class StartQuizPositiveAction extends QAPersistenceAwareAction {

	static Logger LOG = Logger.getLogger(StartQuizPositiveAction.class);
	
	@SuppressWarnings("unchecked")
	protected void doExecute(Object arg) throws Exception {
		
		ContextDAO application = (ContextDAO)arg;
		application.setStatus(ContextDAO.APPROVED);
		
		List<QuestionDAO> questions = new ArrayList<QuestionDAO>();
		
		QuestionDAO q = new QuestionDAO();
		
		q.setQuestionNumber(1);
		q.setCorrectAnswerCount(0);
		q.setQuestion("What is your name?");
		q.setAnswer("c");
		Map<String,String> m1 = new HashMap<String,String>();
		m1.put("a","ben");
		m1.put("b","bon");
		m1.put("c","don");
		q.setSelections(m1);
		questions.add(q);
		
		q = new QuestionDAO();
		q.setQuestionNumber(2);
		q.setCorrectAnswerCount(0);
		q.setQuestion("What is cat?");
		q.setAnswer("c");		
		m1 = new HashMap<String,String>();
		m1.put("a","animal");
		m1.put("b","plant");
		m1.put("c","pet");	
		q.setSelections(m1);
		questions.add(q);
		
		q = new QuestionDAO();
		q.setQuestionNumber(3);
		q.setCorrectAnswerCount(0);
		q.setQuestion("What is phone?");
		q.setAnswer("a");		
		m1 = new HashMap<String,String>();
		m1.put("a","fon");
		m1.put("b","glass");
		m1.put("c","pet");	
		q.setSelections(m1);
		questions.add(q);
		

		DataHandler handler = DataHandler.getInstance(DataHandler.Types.EXCEL);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		
		InputStream is = null; 
		
		List<QuestionDAO> questions2 = null;
		
		if(application.getInputQuestionList() != null ) {
			questions2 = application.getInputQuestionList();
		} else {
			if(application.getInputFile() == null) {
				if(application.getExcelFile() != null && !application.getExcelFile().isEmpty() ) {
					is = new FileInputStream(application.getExcelFile());
				} else {
					is = classloader.getResourceAsStream("excel/sample.xlsx");
				}			
			} else {
				is = application.getInputFile();
			}	
			
			questions2 = (List<QuestionDAO>)handler.loadData(is);
			
		}
		
		LOG.info(questions2.toString());
		questions = questions2;
		
		SessionDAO session = new SessionDAO();
		session.setMemberNumber(application.getMemberNumber());
		session.setExamCode("");
		session.setStartDate(new Date());
		session.setQuestions(questions);
		session.setQuestionSetTotalValue(8); //i.e 8 or 2 or 10
		session.setTotalQuestion(questions.size());
		session.setTotalQuestionRunningValue(1);
		session.setNumberOfSetsDone(1);
		
		application.setQandaSessionDAO(session);
		
		// 1. loop thru all items and find setCorrectAnswerCount < 2
		// 2. add it to array list of integer, the identified index id on #1
		// 3. if result is > 1, shuffle it
		// 4. get the first index
		
		
		LOG.info("getWrongAnswersIndexes: "+session.getWrongAnswers());
		LOG.info("getWrongAnswersRunningValue: "+session.getWrongAnswersRunningValue());
		LOG.info("getQuestionSetRunningValue: "+session.getQuestionSetRunningValue());
		LOG.info("getQuestionSetTotalValue: "+session.getQuestionSetTotalValue());
		
		
		application.setSessiondId(UUID.randomUUID().toString());
		//application.setMemberNumber(application.getMemberNumber());
		
		this.getPersistenceService().saveSessionData(application);

	}

}
