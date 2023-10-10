package com.mpowered.assessmentservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpowered.assessmentservice.entities.AssessmentsSummary;

@Repository
public interface AssessmentMetaRepository extends JpaRepository<AssessmentsSummary,Long>{
	List<AssessmentsSummary> findByMasterPatientid(String masterPatientid, Pageable pageInfo);
	Long countByMasterPatientid(String masterPatientid);
	Optional<AssessmentsSummary> findByInstanceIdAndMasterPatientid(Integer instanceId, String userId);
}
