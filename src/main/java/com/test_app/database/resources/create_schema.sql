CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS request_data (
  id SERIAL PRIMARY KEY,
  data TEXT
);