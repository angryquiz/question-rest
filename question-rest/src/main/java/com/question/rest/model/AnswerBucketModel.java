package com.question.rest.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class AnswerBucketModel {
	
	@ApiModelProperty(position = 1, required = true, value = "Answer")
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "AnswerBucket [answer=" + answer + "]";
	}
		
}
