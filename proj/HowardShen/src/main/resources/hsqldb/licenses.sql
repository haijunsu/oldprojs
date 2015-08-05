DROP TABLE license IF EXISTS;

CREATE TEXT TABLE license (name VARCHAR, type VARCHAR, text VARCHAR);

SET TABLE license SOURCE "licenses.csv";
