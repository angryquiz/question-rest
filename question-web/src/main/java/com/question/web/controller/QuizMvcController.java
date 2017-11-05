package com.question.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.question.services.factory.model.QuizBucket;
import com.question.services.factory.model.SaveAnswerResponse;
import com.question.web.services.QuestionService;

@Controller
public class QuizMvcController {

	private static Logger LOG = Logger.getLogger(QuizMvcController.class);
	
	public static final String QUIZ_WELCOME = "/quiz/welcome";
	public static final String QUIZ_UPLOAD = "/quiz/upload";
	public static final String QUIZ_RESULTS = "/quiz/results/{memberNumber}/{sessionId}";
	public static final String SAVE_ANSWER = "/quiz/save/{questionNumber}/{answer}/{memberNumber}/{sessionId}";

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

	@RequestMapping(value = QUIZ_UPLOAD, method = RequestMethod.POST)
	public String questionUploadFile(ModelMap model, @RequestParam("memberNumber") String memberNumber,
			@RequestParam("file") MultipartFile file) {

		File input = null;
		try {
			input = convert(file);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		LOG.info(input.getAbsolutePath());

		@SuppressWarnings("unchecked")
		Class<? extends List<QuizBucket>> clazz = (Class<? extends List<QuizBucket>>) new ArrayList<QuizBucket>()
				.getClass();

		List<QuizBucket> questionBucketList = questionService
				.put("/rest/quiz/getQuizQuestions/" + memberNumber + "/inputFile", input, clazz);
		
		if (input.delete()) {
			System.out.println(input.getName() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed.");
		}

		LOG.info(QUIZ_UPLOAD + " questionBucketList.size() {} " + questionBucketList.size());

		if (questionBucketList.size() > 0) {
			model.addAttribute("memberNumber", memberNumber);
			model.addAttribute("questionBucketList", questionBucketList);
			model.addAttribute("message", "Answer the following question below?");

		} else {
			model.addAttribute("message", "No result. Error code.");
		}
		return "quiz_upload";
	}

	@RequestMapping(value = QUIZ_WELCOME, method = RequestMethod.GET)
	public String quesitonWelcome(ModelMap model) {
		model.addAttribute("message", "Practice question tool");
		return "quiz_welcome";
	}

	@RequestMapping(value = QUIZ_RESULTS, method = RequestMethod.GET)
	public String showAnswers(ModelMap model, @PathVariable("sessionId") String sessionId,
			@PathVariable("memberNumber") String memberNumber) {

		@SuppressWarnings("unchecked")
		Class<? extends List<QuizBucket>> clazz = (Class<? extends List<QuizBucket>>) new ArrayList<QuizBucket>()
				.getClass();

		List<QuizBucket> questionBucketList = questionService
				.get("/rest/quiz/getQuizResult/" + memberNumber + "/" + sessionId, clazz);

		LOG.info(QUIZ_UPLOAD + " questionBucketList.size() {} " + questionBucketList.size());

		model.addAttribute("memberNumber", memberNumber);
		model.addAttribute("questionBucketList", questionBucketList);
		model.addAttribute("message", "Practice question results.");
		return "quiz_result";
	}

	@RequestMapping(value = SAVE_ANSWER, method = RequestMethod.GET)
	public @ResponseBody SaveAnswerResponse checkAnswer(@PathVariable("answer") String answer,
			@PathVariable("memberNumber") String memberNumber, @PathVariable("sessionId") String sessionId,
			@PathVariable("questionNumber") int questionNumber) {
		SaveAnswerResponse saveAnswerResponse = questionService.get(
				"/rest/quiz/saveAnswer/" + answer + "/" + memberNumber + "/" + sessionId + "/" + questionNumber,
				SaveAnswerResponse.class);
		LOG.info(SAVE_ANSWER.replace("{answer}", answer).replace("{memberNumber}", memberNumber)
				.replace("{sessionId}", sessionId).replace("{questionNumber}", String.valueOf(questionNumber)));
		LOG.info(saveAnswerResponse.toString());
		return saveAnswerResponse;
	}

}
