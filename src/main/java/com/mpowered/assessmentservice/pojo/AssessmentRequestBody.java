package com.mpowered.assessmentservice.pojo;

import java.util.List;

import lombok.Data;

@Data
public class AssessmentRequestBody {
	
	private int questionId;
	private int groupId;
	private List<Answer> answers;
	private String action;

}
