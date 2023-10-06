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