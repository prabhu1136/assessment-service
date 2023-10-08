package com.mpowered.assessmentservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.mpowered.assessmentservice.entities.AssessmentHomeDashboardSummary;
import com.mpowered.assessmentservice.entities.AssessmentsSummary;
import com.mpowered.assessmentservice.pojo.AssessmentMeta;

@Mapper
public interface AssessmentMapper {

	AssessmentMapper INSTANCE = Mappers.getMapper(AssessmentMapper.class);

	@Mapping(target="orgImageURL", source="imgUrl")
	//@Mapping(target="orgImageURL", source="imgUrl")
	AssessmentMeta entityToAssessmentSummaryDTO(AssessmentsSummary assessmentsSummary);
	
	@Mapping(target="orgImageURL", source="imgUrl")
	AssessmentMeta entityHomeDashboardToAssessmentSummary(AssessmentHomeDashboardSummary assessmentsSummary);
}
