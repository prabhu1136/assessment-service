package com.mpowered.assessmentservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpowered.assessmentservice.constant.Constants;

@RestController
@RequestMapping(Constants.URL_V1 + Constants.ASSESSMENT_URL)
public class AssessmentController {

	@GetMapping("/getAllAssessments")
	public List getAssessmentsList() {
		return new ArrayList();
	}
}
