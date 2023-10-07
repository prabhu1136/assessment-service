package com.mpowered.assessmentservice.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentRequestBody {

    private int questionId;
    private int groupId;
    private List<Answer> answers;
    private String action;
}
