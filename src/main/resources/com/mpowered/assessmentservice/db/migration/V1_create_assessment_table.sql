CREATE SCHEMA IF NOT EXISTS assessment_submit_schema;

SET search_path TO assessment_submit_schema;

CREATE TABLE IF NOT EXISTS assessment_submit_schema.assessment_user_question_map (
  "id" serial,
  question_id int8,
  answer_ids varchar(200),
  master_patient_id varchar(200),
  created_at timestamp,
  updated_at tiestamp,
  primary (id)
);