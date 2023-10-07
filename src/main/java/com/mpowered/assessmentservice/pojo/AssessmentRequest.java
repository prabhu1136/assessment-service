package com.mpowered.assessmentservice.pojo;

import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentRequest {
    private Integer assessmentInstanceId;
    private String status;
}