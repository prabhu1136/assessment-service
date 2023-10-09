package com.mpowered.assessmentservice.pojo;

import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentRequest {
    private Integer assessmentInstanceId;
    private AssessmentRequestBody assessmentRequestBody;
    private Pageable pageable;
    private String deviceType;
    private String status;
    private String assessentName;
    private String date;
}