package com.mpowered.assessmentservice.entities;

import com.mpowered.commons.assessment.AssessmentDefinitionDto;
import lombok.*;
import netscape.javascript.JSObject;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assessment_submission",schema = "assessment_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "assessment_id")
    private Integer assessmentId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonb")
//    private List<AssessmentDefinitionDto> assessmentJson;

    @Column(name = "instance_id")
    private Integer instanceId;

    @Column(name = "prepopulate_question_ids")
    private Integer prepopulateQuestionIds;

    @Column(name = "action_trigger_status")
    private String actionTriggerStatus;
}
