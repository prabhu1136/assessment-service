package com.mpowered.assessmentservice.service;

import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import org.springframework.http.ResponseEntity;

public interface AssessmentService {

    ResponseEntity<Object> updateAssessmentStatus(AssessmentRequest assessmentRequest);
}