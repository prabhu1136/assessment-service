package com.mpowered.assessmentservice.pojo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private List<Integer> answerIds;
    private String answerValue;
}
