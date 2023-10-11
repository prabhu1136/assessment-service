package com.mpowered.assessmentservice.service.serviceimpl;

import com.mpowered.assessmentservice.constant.Constants;
import com.mpowered.assessmentservice.entities.AssessmentHomeDashboardSummary;
import com.mpowered.assessmentservice.entities.AssessmentSubmission;
import com.mpowered.assessmentservice.entities.AssessmentsSummary;
import com.mpowered.assessmentservice.pojo.AssessmentFhirResponse;
import com.mpowered.assessmentservice.pojo.AssessmentGridResponse;
import com.mpowered.assessmentservice.pojo.AssessmentMeta;
import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.pojo.AssessmentResponse;
import com.mpowered.assessmentservice.repository.AssessmentHomeDashboardMetaRepository;
import com.mpowered.assessmentservice.repository.AssessmentMetaRepository;
import com.mpowered.assessmentservice.repository.AssessmentSubmissionRepository;
import com.mpowered.assessmentservice.service.AssessmentService;
import com.mpowered.assessmentservice.service.mapper.AssessmentMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentSubmissionRepository assessmentSubmissionRepository;

    private AssessmentMetaRepository assessmentMetaRepository;
    private AssessmentHomeDashboardMetaRepository assessmentHomeDashboardMetaRepository;
    
    public AssessmentServiceImpl(AssessmentSubmissionRepository assessmentSubmissionRepository,
    		AssessmentMetaRepository assessmentMetaRepository, AssessmentHomeDashboardMetaRepository 
    		assessmentHomeDashboardMetaRepository) {
        this.assessmentSubmissionRepository = assessmentSubmissionRepository;
        this.assessmentMetaRepository =assessmentMetaRepository;
        this.assessmentHomeDashboardMetaRepository = assessmentHomeDashboardMetaRepository;
    }

    @Override
    public ResponseEntity<Object> updateAssessmentStatus(AssessmentRequest assessmentRequest) {
        try {
            Integer assessmentInstanceId = assessmentRequest.getAssessmentInstanceId();
            Optional<AssessmentSubmission> submission = assessmentSubmissionRepository.findById(assessmentInstanceId);
            if (submission.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
                submission.get().setStatus(assessmentRequest.getStatus());
                assessmentSubmissionRepository.save(submission.get());
                return ResponseEntity.ok(String.format("Status updated successfully to %s",assessmentRequest.getStatus()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to update status");
        }

    }
    
    @Override
	public AssessmentGridResponse getAllAssessments(String userId, AssessmentRequest assessmentRequest){
    	/*int offset = assessmentRequest.getPageable().getOffset();
    	int count = assessmentRequest.getPageable().getCount();*/

    	return getAllGridAssessments(userId, assessmentRequest);
	}
    
    @Override
    public AssessmentResponse getAssessmentMetadata(String userId, Integer instanceId) {
    	log.info("fetching data for instance {} and user id {}.", instanceId, userId);
    	Optional<AssessmentsSummary> assessSummary =assessmentMetaRepository.findByInstanceIdAndMasterPatientid(instanceId, userId);
    	if(assessSummary.isPresent()) {
    		AssessmentMapper assesmentMapper = AssessmentMapper.INSTANCE;
	    	AssessmentResponse assessmentResponse = new AssessmentResponse();
    		log.info("returning data for patient id {}.", assessSummary.get().getMasterPatientid());
	    	AssessmentMeta assessmentMeta = assesmentMapper.entityToAssessmentSummaryDTO(assessSummary.get());
			assessmentResponse.setAssessmentMeta(assessmentMeta);
			return assessmentResponse;
    	}
    	return new AssessmentResponse();
    }
    
    private AssessmentGridResponse getAllGridAssessments(String userId, AssessmentRequest assessmentRequest){
    	int offset = assessmentRequest.getPageable().getOffset();
    	int count = assessmentRequest.getPageable().getCount();
    	List<AssessmentsSummary>  assessmentEntity = assessmentMetaRepository.findByMasterPatientid(userId,
				PageRequest.of(offset, count));
    	log.info("got {} assessments for user {}.", (assessmentEntity != null ? assessmentEntity.size() +"":"null"),
				userId);
		AssessmentMapper assesmentMapper = AssessmentMapper.INSTANCE;
		List<AssessmentFhirResponse> responses = new ArrayList<>();
		for(AssessmentsSummary assessmentsummary: assessmentEntity) {
			AssessmentMeta assessmentMeta = assesmentMapper.entityToAssessmentSummaryDTO(assessmentsummary);
			AssessmentResponse assessmentResponse = new AssessmentResponse();
			assessmentResponse.setAssessmentMeta(assessmentMeta);
			AssessmentFhirResponse assessmentFhirResponse = new AssessmentFhirResponse();
			assessmentFhirResponse.setAssessmentResponse(assessmentResponse);
			assessmentFhirResponse.setId(assessmentsummary.getInstanceId().toString() );
			assessmentFhirResponse.setResourceType("Assessment");
			responses.add(assessmentFhirResponse);
		}
		long total = assessmentMetaRepository.countByMasterPatientid(userId);
		log.info("assessments response size {}, {}.", responses.size(), total);
		AssessmentGridResponse assessmentGridResponse = new AssessmentGridResponse();
		assessmentGridResponse.setAssessments(responses);
		assessmentGridResponse.setTotal(total);
		return assessmentGridResponse;
    }
    
    @Override
    public List<AssessmentResponse> getAllHomeDashboardAssessments(String userId, AssessmentRequest assessmentRequest){
    	int offset = assessmentRequest.getPageable().getOffset();
    	int count = assessmentRequest.getPageable().getCount();
    	List<AssessmentHomeDashboardSummary>  assessmentEntity = assessmentHomeDashboardMetaRepository.findByMasterPatientid(userId,
				PageRequest.of(offset, count));
    	log.info("got {} HB assessments for user {}.", (assessmentEntity != null ? assessmentEntity.size() +"":"null"),
				userId);
    	AssessmentMapper assesmentMapper = AssessmentMapper.INSTANCE;
		List<AssessmentResponse> responses = new ArrayList<>();
		for(AssessmentHomeDashboardSummary assessmentHBsummary: assessmentEntity) {
			AssessmentMeta assessmentMeta = assesmentMapper.entityHomeDashboardToAssessmentSummary(assessmentHBsummary);
			AssessmentResponse assessmentResponse = new AssessmentResponse();
			assessmentResponse.setAssessmentMeta(assessmentMeta);
			responses.add(assessmentResponse);
		}
		log.info("HB assessments response size {}.", responses.size());
		return responses;
    }

	@Override
	public AssessmentGridResponse getAssessmentByInstanceId(AssessmentRequest assessmentRequest) {
		Optional<AssessmentsSummary> assessSummary =assessmentMetaRepository.findByInstanceId(assessmentRequest.getAssessmentInstanceId());
		if(assessSummary.isPresent()) {
			AssessmentsSummary assessmentsSummary = assessSummary.get();
			List<AssessmentFhirResponse> assessmentResponses = new ArrayList<>();
    		AssessmentMapper assesmentMapper = AssessmentMapper.INSTANCE;
	    	AssessmentMeta assessmentMeta = assesmentMapper.entityToAssessmentSummaryDTO(assessmentsSummary);
			AssessmentResponse assessmentResponse = new AssessmentResponse();
			assessmentResponse.setAssessmentMeta(assessmentMeta);
			AssessmentFhirResponse assessmentFhirResponse = new AssessmentFhirResponse();
			assessmentFhirResponse.setAssessmentResponse(assessmentResponse);
			assessmentFhirResponse.setId(assessmentsSummary.getInstanceId().toString() );
			assessmentFhirResponse.setResourceType("Assessment");
			AssessmentGridResponse assessmentGridResponse = new AssessmentGridResponse();
			assessmentResponses.add(assessmentFhirResponse);
			assessmentGridResponse.setAssessments(assessmentResponses);
			return assessmentGridResponse;
    	}
    	return new AssessmentGridResponse();
	}
}