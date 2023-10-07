package com.mpowered.assessmentservice.repository;


import com.mpowered.assessmentservice.entities.AssessmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentSubmissionRepository extends JpaRepository<AssessmentSubmission, Integer> {
    Optional<AssessmentSubmission> findById(Integer Id);
}
