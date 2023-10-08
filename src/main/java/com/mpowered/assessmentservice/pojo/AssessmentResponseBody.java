package com.mpowered.assessmentservice.pojo;

import java.util.List;

import lombok.Data;

@Data
public class AssessmentResponseBody {
	
	private List<AssessmentQuestionnaire> assessmentQuestionnaires;
	private int answeredQuestionsCount;

}
