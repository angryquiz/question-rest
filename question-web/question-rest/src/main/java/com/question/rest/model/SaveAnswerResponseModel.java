package com.question.rest.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class SaveAnswerResponseModel {
	
	@ApiModelProperty(position = 1, required = true, value = "code")
	private int code;
	
	@ApiModelProperty(position = 2, required = true, value = "description")
	private String description;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SaveAnswerResponse [code=" + code + ", description="
				+ description + "]";
	}
	
	
}
