package com.mpowered.assessmentservice.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="assessment_grid_view", schema = "assessment_schema")
@NamedQuery(name="AssessmentsSummary.findAll", query="SELECT c FROM AssessmentsSummary c")
@Setter
@Getter
@AllArgsConstructor
public class AssessmentsSummary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public AssessmentsSummary() {
	}

	@Id
	@Column(name="instance_id")
	private Integer instanceId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="assessment_id")
	private Integer assessmentId;

	@Column(name="master_patient_id")
	private String masterPatientid;

	
	@Column(name="org_id")
	private Integer orgId;
	
	@Column(name="orgname")
	private String orgName;
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="assesmentname")
	private String assessmentName;
	
	@Column(name="assigndate")
	private Timestamp assignedDate;

	@Column(name="instance_expiry_date")
	private Date expiryDate;

}
