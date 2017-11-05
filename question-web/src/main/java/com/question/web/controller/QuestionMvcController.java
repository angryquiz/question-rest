package com.question.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.question.services.factory.model.AnswerBucket;
import com.question.services.factory.model.QuestionBucket;
import com.question.web.services.QuestionService;

@Controller
public class QuestionMvcController {

	private static Logger LOG = Logger.getLogger(QuestionMvcController.class);
	
	public static final String QUESTION_UPLOAD = "/question/upload";
	public static final String WELCOME_QUESTION = "/question/welcome";
	public static final String NEXT_QUESTION = "/question/next/{memberNumber}/{sessionId}";
	public static final String REVIEW_ANSWER = "/question/review/{memberNumber}/{sessionId}";
	public static final String CHECK_ANSWER = "/question/check/{questionNumber}/{answer}/{memberNumber}/{sessionId}";

	@Inject
	private QuestionService questionService;

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@RequestMapping(value = QUESTION_UPLOAD, method = RequestMethod.POST)
	public String questionUploadFile(ModelMap model, @RequestParam("memberNumber") String memberNumber,
			@RequestParam("file") MultipartFile file) {

		File input = null;
		try {
			input = convert(file);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		LOG.info(input.getAbsolutePath());

		QuestionBucket questionBucket2 = questionService
				.put("/rest/questions/getFirstQuestion/" + memberNumber + "/inputFile", input, QuestionBucket.class);

		if (input.delete()) {
			LOG.info(input.getName() + " is deleted!");
		} else {
			LOG.info("Delete operation is failed.");
		}

		model.addAttribute("memberNumber", memberNumber);
		model.addAttribute("question", questionBucket2);
		model.addAttribute("message", "Question detail below?");

		return "question_upload";
	}


	@RequestMapping(value = WELCOME_QUESTION, method = RequestMethod.GET)
	public String quesitonWelcome(ModelMap model) {
		model.addAttribute("message", "Memorize question tool.");
		return "question_welcome";
	}

	@RequestMapping(value = NEXT_QUESTION, method = RequestMethod.GET)
	public String nextQuestion(ModelMap model, @PathVariable("sessionId") String sessionId,
			@PathVariable("memberNumber") String memberNumber) {

		QuestionBucket questionBucket = questionService
				.get("/rest/questions/getNextQuestion/" + memberNumber + "/" + sessionId, QuestionBucket.class);
		LOG.info(questionBucket.toString());

		model.addAttribute("memberNumber", memberNumber);
		model.addAttribute("question", questionBucket);
		model.addAttribute("message", "Memorize question tool.");
		return "question_next";
	}

	@RequestMapping(value = REVIEW_ANSWER, method = RequestMethod.GET)
	public String showAnswers(ModelMap model, @PathVariable("sessionId") String sessionId,
			@PathVariable("memberNumber") String memberNumber) {

		QuestionBucket questionBucket = questionService
				.get("/rest/questions/getWrongAnswer/" + memberNumber + "/" + sessionId, QuestionBucket.class);
		LOG.info(questionBucket.toString());

		model.addAttribute("memberNumber", memberNumber);
		model.addAttribute("question", questionBucket);
		model.addAttribute("message", "Memorize question tool. Review answer below.");
		return "question_review_answer";
	}

	@RequestMapping(value = CHECK_ANSWER, method = RequestMethod.GET)
	public @ResponseBody AnswerBucket checkAnswer(@PathVariable("answer") String answer,
			@PathVariable("memberNumber") String memberNumber, @PathVariable("sessionId") String sessionId,
			@PathVariable("questionNumber") int questionNumber) {
		AnswerBucket answerBucket = questionService.get(
				"/rest/questions/checkAnswer/" + answer + "/" + memberNumber + "/" + sessionId + "/" + questionNumber,
				AnswerBucket.class);
		LOG.info(answerBucket.toString());
		return answerBucket;
	}

}
