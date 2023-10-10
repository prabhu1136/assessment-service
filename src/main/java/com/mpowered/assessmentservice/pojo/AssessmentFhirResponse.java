package com.mpowered.assessmentservice.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentFhirResponse {
	private String resourceType;
	private String id;
	private AssessmentResponse assessmentResponse;
}
