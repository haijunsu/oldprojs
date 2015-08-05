DROP TABLE term IF EXISTS;

CREATE TEXT TABLE term (key VARCHAR);

SET TABLE term SOURCE "terms.csv";
