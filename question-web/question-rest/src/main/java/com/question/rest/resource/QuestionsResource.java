package com.question.rest.resource;

import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.rest.exception.EntityException;
import com.question.rest.model.AnswerBucketModel;
import com.question.rest.model.QuestionBucketDetailsModel;
import com.question.rest.model.QuestionBucketModel;
import com.question.rest.model.QuestionList;
import com.question.rest.service.QuestionService;
import com.question.rest.util.Utils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST endpoint for user manipulation.
 * http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */
@Api(value = "questions", description = "Endpoint for questions")
@Path("/questions")
public class QuestionsResource {

	static Logger LOG = Logger.getLogger(QuestionsResource.class);
	
	@Context
	private UriInfo uriInfo;

	@Inject
	private QuestionService questionService;

	@Inject
	private Utils utils;

	@GET
	@Path("/checkAnswer/{answer}/{memberNumber}/{sessionId}/{questionNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Trigger getWrongAnswer operation", notes = "Trigger getWrongAnswer operation.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkAnswer(@HeaderParam("Authorization") String token, @PathParam("answer") String answer,
			@PathParam("memberNumber") String memberNumber, @PathParam("sessionId") String sessionId,
			@PathParam("questionNumber") int questionNumber) {

		//utils.validateToken(token);

		try {

			AnswerBucket answerBucket = questionService.checkAnswer(answer, memberNumber, sessionId, questionNumber);

			LOG.info(answerBucket.getAnswer());

			AnswerBucketModel model = new AnswerBucketModel();
			model.setAnswer(answerBucket.getAnswer());

			return Response.status(Status.CREATED).entity(model).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}

	@GET
	@Path("/getWrongAnswer/{memberNumber}/{sessionId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Trigger getWrongAnswer operation", notes = "Trigger getWrongAnswer operation.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getWrongAnswer(@HeaderParam("Authorization") String token,
			@PathParam("memberNumber") String memberNumber, @PathParam("sessionId") String sessionId) {

		//utils.validateToken(token);

		try {

			QuestionBucket questionBucket = questionService.getWrongAnswer(memberNumber, sessionId);

			LOG.info(questionBucket.toString());
			LOG.info(questionBucket.getStatus());

			return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}

	@PUT
	@Path("/getFirstQuestion/{memberNumber}/inputFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiImplicitParams(@ApiImplicitParam(dataType = "file", name = "inputFile", paramType = "body"))
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Trigger getFirstQuestion operation", notes = "Trigger getFirstQuestion operation.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getFirstQuestion(@HeaderParam("Authorization") String token,
			@PathParam("memberNumber") String memberNumber,
			@ApiParam(access = "hidden") @FormDataParam("inputFile") InputStream inputFile) {

		//utils.validateToken(token);

		try {

			QuestionBucket questionBucket = questionService.getFirstQuestion(inputFile, memberNumber);

			LOG.info(questionBucket.toString());
			LOG.info(questionBucket.getStatus());

			return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}

	/**
	 * Creates and registers user in application.
	 */
	@GET
	@Path("/getFirstQuestion/{memberNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Trigger getFirstQuestion operation", notes = "Trigger getFirstQuestion operation.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getFirstQuestion(@HeaderParam("Authorization") String token,
			@PathParam("memberNumber") String memberNumber) {

		//utils.validateToken(token);

		try {

			QuestionBucket questionBucket = questionService.getFirstQuestion("", memberNumber);

			LOG.info(questionBucket.toString());
			LOG.info(questionBucket.getStatus());

			return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}

	/**
	 * Creates and registers user in application.
	 */
	@GET
	@Path("/getNextQuestion/{memberNumber}/{sessionId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Trigger getNextQuestion operation", notes = "Trigger getNextQuestion operation.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getNextQuestion(@HeaderParam("Authorization") String token,
			@PathParam("memberNumber") String memberNumber, @PathParam("sessionId") String sessionId) {

		//utils.validateToken(token);

		try {

			QuestionBucket questionBucket = questionService.getNextQuestion(memberNumber, sessionId);

			LOG.info(questionBucket.toString());
			LOG.info(questionBucket.getStatus());

			return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}

	@PUT
	@Path("/submitQuestionList/{memberNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "QuestionList", notes = "QuestionList.", response = QuestionList.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class),
			@ApiResponse(code = 406, message = "Malformed definition"),
			@ApiResponse(code = 409, message = "Already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response uploadList(@HeaderParam("Authorization") String token, 
			@PathParam("memberNumber") String memberNumber,
			@ApiParam(name = "questions", required = true) QuestionList questions) {

		//utils.validateToken(token);

		try {

			QuestionBucket questionBucket = questionService.getOneQuestion(questions.getQuestions(), memberNumber);


			return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
		} catch (Exception e) {
			throw new EntityException(Status.NOT_FOUND, 99, e.getMessage());
		}
	}
	
	private QuestionBucketModel toModel(QuestionBucket questionBucket) {
		QuestionBucketModel model = new QuestionBucketModel();
		model.setAnswer(questionBucket.getAnswer());
		model.setQuestionType(questionBucket.getQuestionType());
		model.setExplanation(questionBucket.getExplanation());
		model.setQuestion(questionBucket.getQuestion());
		QuestionBucketDetailsModel questionBucketDetailsModel = new QuestionBucketDetailsModel();
		questionBucketDetailsModel.setNumberOfSetsDone(questionBucket.getQuestionBucketDetails().getNumberOfSetsDone());
		questionBucketDetailsModel.setQuestionNumber(questionBucket.getQuestionBucketDetails().getQuestionNumber());
		questionBucketDetailsModel
				.setQuestionSetRunningValue(questionBucket.getQuestionBucketDetails().getQuestionSetRunningValue());
		questionBucketDetailsModel
				.setQuestionSetTotalValue(questionBucket.getQuestionBucketDetails().getQuestionSetTotalValue());
		questionBucketDetailsModel.setTotalQuestion(questionBucket.getQuestionBucketDetails().getTotalQuestion());
		questionBucketDetailsModel
				.setTotalQuestionRunningValue(questionBucket.getQuestionBucketDetails().getTotalQuestionRunningValue());
		model.setQuestionBucketDetails(questionBucketDetailsModel);
		model.setQuestionNumber(questionBucket.getQuestionNumber());
		model.setSelection(questionBucket.getSelection());
		model.setSessionId(questionBucket.getSessionId());
		model.setAnswersMap(questionBucket.getAnswersMap());
		model.setQuestionsMap(questionBucket.getQuestionsMap());

		if (questionBucket.getStatus() == null) {
			model.setStatus(QuestionBucketStatus.STATUS_NULL_QUESTIONS_NOT_ANSWERED);
		} else if (questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_AVAILABLE)) {
			model.setStatus(QuestionBucketStatus.QUESTION_AVAILABLE);
		} else if (questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED)) {
			model.setStatus(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED);
		} else if (questionBucket.getStatus().equals(QuestionBucketStatus.WRONG_ANSWER_FOUND)) {
			model.setStatus(QuestionBucketStatus.WRONG_ANSWER_FOUND);
		}
		return model;
	}

}
