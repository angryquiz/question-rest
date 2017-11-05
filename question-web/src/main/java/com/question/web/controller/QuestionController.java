package com.question.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/questions")
public class QuestionController {

	static Logger LOG = Logger.getLogger(QuestionController.class);

	// http://localhost:9999/question-web/rest/questions/getFirstQuestion/1/
	// http://localhost:9999/question-web/rest/questions/getNextQuestion/1/ff726f72-f068-4109-a8c2-f631b6438709/

	@Inject
	private QuestionService questionService;

	// https://spring.io/guides/gs/uploading-files/
	// http://docs.spring.io/spring/docs/3.1.x/javadoc-api/org/springframework/context/annotation/Configuration.html
	// http://www.mkyong.com/spring-mvc/spring-mvc-file-upload-example/
	// http://www.mkyong.com/spring-mvc/spring-mvc-file-upload-example/

	private void writeByteArrayToFile(byte[] t, String strFilePath) {

		// String strFilePath = "C://FileIO//demo.txt";

		try {
			FileOutputStream fos = new FileOutputStream(strFilePath);
			String strContent = "Write File using Java FileOutputStream example !";

			/*
			 * To write byte array to a file, use void write(byte[] bArray)
			 * method of Java FileOutputStream class.
			 *
			 * This method writes given byte array to a file.
			 */

			// fos.write(strContent.getBytes());

			fos.write(t);

			/*
			 * Close FileOutputStream using, void close() method of Java
			 * FileOutputStream class.
			 *
			 */

			fos.close();

		} catch (FileNotFoundException ex) {
			LOG.info("FileNotFoundException : " + ex);
		} catch (IOException ioe) {
			LOG.info("IOException : " + ioe);
		}

	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@RequestMapping(value = "/getFirstQuestion/{memberNumber}/inputFile", method = RequestMethod.POST)
	public @ResponseBody QuestionBucket handleFileUpload(@PathVariable("memberNumber") String memberNumber,
			@RequestParam("file") MultipartFile file) {
		String name = "Unknown";
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

		return questionBucket2;
	}

	@RequestMapping(value = "/getFirstQuestion/{memberNumber}", method = RequestMethod.GET)
	public @ResponseBody QuestionBucket getFirstQuestion(@PathVariable String memberNumber) {
		QuestionBucket questionBucket = questionService.get("/rest/questions/getFirstQuestion/" + memberNumber,
				QuestionBucket.class);
		LOG.info(questionBucket.toString());
		return questionBucket;
	}

	@RequestMapping(value = "/getNextQuestion/{memberNumber}/{sessionId}", method = RequestMethod.GET)
	public @ResponseBody QuestionBucket getNextQuestion(@PathVariable("memberNumber") String memberNumber,
			@PathVariable("sessionId") String sessionId) {
		QuestionBucket questionBucket = questionService
				.get("/rest/questions/getNextQuestion/" + memberNumber + "/" + sessionId, QuestionBucket.class);
		LOG.info(questionBucket.toString());
		return questionBucket;
	}

	@RequestMapping(value = "/getWrongAnswer/{memberNumber}/{sessionId}", method = RequestMethod.GET)
	public @ResponseBody QuestionBucket getWrongAnswer(@PathVariable("memberNumber") String memberNumber,
			@PathVariable("sessionId") String sessionId) {
		QuestionBucket questionBucket = questionService
				.get("/rest/questions/getWrongAnswer/" + memberNumber + "/" + sessionId, QuestionBucket.class);
		LOG.info(questionBucket.toString());
		return questionBucket;
	}

	@RequestMapping(value = "/checkAnswer/{answer}/{memberNumber}/{sessionId}/{questionNumber}", method = RequestMethod.GET)
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