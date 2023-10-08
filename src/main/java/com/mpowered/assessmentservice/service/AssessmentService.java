package com.mpowered.assessmentservice.service;

import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.pojo.AssessmentResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface AssessmentService {

    ResponseEntity<Object> updateAssessmentStatus(AssessmentRequest assessmentRequest);
    List<AssessmentResponse> getAllAssessments(String userId, AssessmentRequest assessmentRequest, boolean homeDashboard);
    AssessmentResponse getAssessmentMetadata(String userId, Integer instanceId);
}