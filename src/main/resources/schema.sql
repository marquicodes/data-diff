CREATE SCHEMA IF NOT EXISTS demo;

USE demo;

CREATE TABLE IF NOT EXISTS diff (
   id INT PRIMARY KEY,
   left CLOB null,
   right CLOB null
);