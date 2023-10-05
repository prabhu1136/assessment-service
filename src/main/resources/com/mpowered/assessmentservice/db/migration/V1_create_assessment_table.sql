CREATE SCHEMA IF NOT EXISTS assessment_submit_schema;

SET search_path TO assessment_submit_schema;

CREATE TABLE IF NOT EXISTS assessment_submit_schema.assessment_submission (
  "id" serial,
  master_patient_id int8,
  status varchar(30),
  created_at timestamp,
  created_by varchar(200),
  updated_at timestamp,
  updated_by varchar(200),
  is_active boolean,
  response_json json,
  instance_id int8,
  prepopulate_question_ids varchar (500),
  action_trigger_status varchar(100),
  primary key(id)
);


CREATE TABLE IF NOT EXISTS assessment_submit_schema.assessment_user_question_map (
  "id" serial,
  question_id int8,
  answer_ids varchar(200),
  master_patient_id varchar(200),
  created_at timestamp,
  updated_at tiestamp,
  primary (id)
);