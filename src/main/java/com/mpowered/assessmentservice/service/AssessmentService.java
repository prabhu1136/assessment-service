package com.mpowered.assessmentservice.service;

import com.mpowered.assessmentservice.pojo.AssessmentFhirResponse;
import com.mpowered.assessmentservice.pojo.AssessmentGridResponse;
import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.pojo.AssessmentResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface AssessmentService {

    ResponseEntity<Object> updateAssessmentStatus(AssessmentRequest assessmentRequest);
    AssessmentGridResponse getAllAssessments(String userId, AssessmentRequest assessmentRequest);
    AssessmentResponse getAssessmentMetadata(String userId, Integer instanceId);
    List<AssessmentResponse> getAllHomeDashboardAssessments(String userId, AssessmentRequest assessmentRequest);
    AssessmentFhirResponse getAssessmentByInstanceId(AssessmentRequest assessmentRequest);
}