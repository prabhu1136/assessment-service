package com.mpowered.assessmentservice.service.serviceimpl;

import com.mpowered.assessmentservice.constant.Constants;
import com.mpowered.assessmentservice.entities.AssessmentSubmission;
import com.mpowered.assessmentservice.pojo.AssessmentRequest;
import com.mpowered.assessmentservice.repository.AssessmentSubmissionRepository;
import com.mpowered.assessmentservice.service.AssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentSubmissionRepository assessmentSubmissionRepository;

    public AssessmentServiceImpl(AssessmentSubmissionRepository assessmentSubmissionRepository) {
        this.assessmentSubmissionRepository = assessmentSubmissionRepository;
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
                return ResponseEntity.ok("Status updated successfully to 'draft'");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to update status");
        }

    }
}