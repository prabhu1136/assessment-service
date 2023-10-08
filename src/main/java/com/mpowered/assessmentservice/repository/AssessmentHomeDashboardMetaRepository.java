package com.mpowered.assessmentservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpowered.assessmentservice.entities.AssessmentHomeDashboardSummary;
import com.mpowered.assessmentservice.entities.AssessmentsSummary;

@Repository
public interface AssessmentHomeDashboardMetaRepository extends JpaRepository<AssessmentHomeDashboardSummary,Long>{
	List<AssessmentHomeDashboardSummary> findByMasterPatientid(String masterPatientid, Pageable pageInfo);
	Optional<AssessmentHomeDashboardSummary> findByInstanceIdAndMasterPatientid(Integer instanceId, String userId);
}
