package com.mpowered.assessmentservice.pojo;

import lombok.Data;

@Data
public class AssessmentResponse {
	
	private AssessmentMeta assessmentMeta;
	private AssessmentResponseBody assessmentResponseBody;
	private Pageable pageable;

}
