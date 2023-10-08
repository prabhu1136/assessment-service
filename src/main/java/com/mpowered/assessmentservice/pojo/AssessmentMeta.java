package com.mpowered.assessmentservice.pojo;

import lombok.Data;

@Data
public class AssessmentMeta {
	
	private String assessmentName;
    private String status;
    private String orgImageURL;
    private String orgName;
    private Integer numberOfQuestions;
    private String assignedDate;
    private String expiryDate;
    private String completedDate;
    private Long instanceId;
}
