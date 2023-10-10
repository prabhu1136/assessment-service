package com.mpowered.assessmentservice.pojo;

import java.util.List;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentGridResponse {
	List<AssessmentFhirResponse> assessments;
	Long total;
}
