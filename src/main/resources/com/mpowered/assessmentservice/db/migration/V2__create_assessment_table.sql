SET search_path TO assessment_schema;

CREATE TABLE IF NOT EXISTS assessment_schema.assessment_user_question_map (
  "id" serial,
  question_id int8,
  answer_ids varchar(200),
  answer_text varchar(1000),
  master_patient_id varchar(200),
  created_at timestamp,
  updated_at timestamp,
  primary key(id),
  CONSTRAINT assessment_user_question_map_fkey FOREIGN KEY (question_id) REFERENCES assessment_schema.question(id)
);

CREATE or REPLACE VIEW assessment_schema.assessment_grid_view as(Select as2.instance_id, as2.status,
as2.assessment_id, as2.master_patient_id,  ac.org_id, o.name as orgname, o.img_url, ac.name as assesmentname,
as2.created_at as assigndate, am.instance_expiry_date, as2.updated_at
from assessment_schema.assessment_submission as2, assessment_schema.assessment_config ac, partner_schema.organization o,
assessment_schema.assessment_instance_map am
where as2.assessment_id = ac.id and am.instance_id = as2.instance_id and ac.org_id = o.id
order by 
case when as2.status ='PENDING' then 1
	when as2.status = 'DRAFT' then 2
	when as2.status = 'COMPLETED' then 3
	when as2.status = 'EXPIRED' then 4
end asc,
instance_expiry_date asc);

CREATE or REPLACE VIEW assessment_schema.assessment_homedashboard_view as(Select as2.instance_id, as2.status,
as2.assessment_id, as2.master_patient_id,  ac.org_id, o.name as orgname, o.img_url, ac.name as assesmentname,
as2.created_at as assigndate, am.instance_expiry_date, as2.updated_at
from assessment_schema.assessment_submission as2, assessment_schema.assessment_config ac, partner_schema.organization o,
assessment_schema.assessment_instance_map am
where as2.assessment_id = ac.id and am.instance_id = as2.instance_id and ac.org_id = o.id and
upper(as2.status) not in ('COMPLETED', 'EXPIRED')
order by 
case when as2.status ='PENDING' then 1
	when as2.status = 'DRAFT' then 2
	when as2.status = 'COMPLETED' then 3
	when as2.status = 'EXPIRED' then 4
end asc,
assigndate asc);