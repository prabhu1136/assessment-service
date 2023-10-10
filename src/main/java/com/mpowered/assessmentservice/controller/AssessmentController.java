package com.mpowered.assessmentservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mpowered.assessmentservice.pojo.AssessmentGridResponse;
import com.mpowered.assessmentservice.pojo.AssessmentMeta;
import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.pojo.AssessmentResponse;
import com.mpowered.assessmentservice.pojo.Pageable;
import com.mpowered.assessmentservice.pojo.AssessmentFhirResponse;
import com.mpowered.assessmentservice.service.AssessmentService;
import com.mpowered.commons.pojo.MpoweredUser;
import com.mpowered.commons.pojo.userpojo.UserSessionDto;
//import com.mpowered.commons.security.AuthHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mpowered.assessmentservice.constant.Constants;

@RestController
@RequestMapping(Constants.URL_V1 + Constants.ASSESSMENT_URL)
public class AssessmentController {
	Logger log = LoggerFactory.getLogger(getClass());
	
	private AssessmentService assessmentService;
	//private final AuthHelper authHelper;

	public AssessmentController(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}

	@PostMapping("/all")
	public AssessmentGridResponse getAssessmentsList(@RequestHeader Map<String, Object> headers,
			@RequestBody AssessmentRequest assessmentRequest) {
		//Optional<MpoweredUser> mpoweredUser = authHelper.registerMpoweredUser(authentication);
		log.info("getting all assessments");
		String userKcId= getUserIdFromHeader(headers);
		if (! userKcId.isEmpty()) {
			log.info("getting assessments for user: {}", userKcId);
			/*AssessmentRequest assessmentRequest = new AssessmentRequest();
			Pageable pageable =new Pageable();
			pageable.setCount(count);
			pageable.setOffset(offset);
			assessmentRequest.setPageable(pageable);
			assessmentRequest.setAssessentName(assessmentname);
			assessmentRequest.setStatus(assessmentStatus);
			assessmentRequest.setDate(dateParam);*/
			return assessmentService.getAllAssessments( userKcId, assessmentRequest);
		}
		return new AssessmentGridResponse();
	}

	@GetMapping
	public AssessmentFhirResponse getAssessmentsByInstanceId(@RequestBody AssessmentRequest assessmentRequest) {
		log.info("get assessment by instanceId");
		return assessmentService.getAssessmentByInstanceId(assessmentRequest);
	}

	@PostMapping("/status")
	public ResponseEntity<Object> updateAssessmentStatus(@RequestBody AssessmentRequest assessmentRequest) {
		return assessmentService.updateAssessmentStatus(assessmentRequest);
	}

	@GetMapping ("metadata")
	public AssessmentResponse getAssessmentMetaData(@RequestParam("instanceId")Integer instanceId,
			@RequestHeader Map<String, Object> headers) {
		log.info("getting meta data");
		String userKcId= getUserIdFromHeader(headers);
		if (! userKcId.isEmpty()) {
			log.info("fetching assessment metdata for user: {}", userKcId);
			return assessmentService.getAssessmentMetadata(userKcId, instanceId);
		}
		return new AssessmentResponse();
	}

	private String getUserIdFromHeader(Map<String, Object> headers) {
			String userSessionStr = String.valueOf(headers.get(UserSessionDto.X_USER));
			String formatted= convertUserStringtoJson(userSessionStr);
			ObjectReader objectReader = JsonMapper.builder().enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES).build()
					.readerFor(UserSessionDto.class);
			try {
				UserSessionDto userSessionDto = objectReader.readValue(formatted);
				return userSessionDto.getUserKeycloakId();
			} catch (JsonProcessingException e) {
				log.error("Failed to get user information from header {}", userSessionStr, e);
				return "";
			} 
	}
	private String convertUserStringtoJson(String input) {
		String [] fields= input.split(",");
		for (int indx =0; indx < fields.length ; indx++) {
			String field1 = fields[indx];
			if(field1.contains("{")) {
				field1 = field1.replace("{", "{\"");
			}else
				field1 = "\"" + field1; 
				
			field1 =field1.replace(":", "\":\"");
			if(field1.contains("}")) {
				field1 = field1.replace("}", "\"}");
			}else
				field1 =field1.concat("\"");
			
			fields[indx] = field1;
		}
		return String.join(",", fields);
	}

	@GetMapping("/allhomedashboard/{offset}/{count}")
	public List<AssessmentResponse> getAssessmentsHomeDashboard(@RequestHeader Map<String, Object> headers,
			@PathVariable("offset") Integer offset, @PathVariable("count") Integer count) {
		log.info("getting assessments for homedashboard");
		String userKcId= getUserIdFromHeader(headers);
		if (! userKcId.isEmpty()) {
			log.info("getting assessments for homedashboard for user: {}", userKcId);
			AssessmentRequest assessmentRequest = new AssessmentRequest();
			Pageable pageable = new Pageable();
			pageable.setCount(count);
			pageable.setOffset(offset);
			assessmentRequest.setPageable(pageable);
			return assessmentService.getAllHomeDashboardAssessments( userKcId, assessmentRequest);
		}
		return new ArrayList<>();
	}
	
}
