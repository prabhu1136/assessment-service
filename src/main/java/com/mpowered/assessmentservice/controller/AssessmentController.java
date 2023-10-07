package com.mpowered.assessmentservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.service.AssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mpowered.assessmentservice.constant.Constants;

@RestController
@RequestMapping(Constants.URL_V1 + Constants.ASSESSMENT_URL)
public class AssessmentController {
	Logger log = LoggerFactory.getLogger(getClass());
	private static AssessmentService assessmentService;


	public AssessmentController(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}

	@GetMapping("/all")
	public List getAssessmentsList() {
		return new ArrayList();
	}

	@PostMapping("/status")
	public ResponseEntity<Object> updateAssessmentStatus(@RequestBody AssessmentRequest assessmentRequest) {
		return assessmentService.updateAssessmentStatus(assessmentRequest);
	}
}
