package com.mpowered.assessmentservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpowered.assessmentservice.constant.Constants;

@RestController
@RequestMapping(Constants.URL_V1 + Constants.ASSESSMENT_URL)
public class AssessmentController {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/all")
	public List getAssessmentsList() {
		return new ArrayList();
	}
}
