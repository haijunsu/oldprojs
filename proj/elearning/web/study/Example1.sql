DROP TABLE testtree;

CREATE TABLE testtree (
  city varchar(20),
  cityurl varchar(80),
  area varchar(20),
  areaurl varchar(80),
  street varchar(20),
  streeturl varchar(80)
);

INSERT INTO testtree VALUES ('Edinburgh',NULL,'Morningside',NULL,'Comiston Rd','DestinationPane.jsp?street=COMISTON+ROAD');
INSERT INTO testtree VALUES ('Edinburgh',NULL,'Morningside',NULL,'Morningside Park','DestinationPane.jsp?street=MORNINGSIDE+PARK');
INSERT INTO testtree VALUES ('Edinburgh',NULL,'Leith','DestinationPane.jsp?area=LEITH','Pitt St','DestinationPane.jsp?street=PITT+STREET');
INSERT INTO testtree VALUES ('Edinburgh',NULL,'Leith','DestinationPane.jsp?area=LEITH','Brunswick Place','DestinationPane.jsp?street=BRUNSWICK+PLACE');
INSERT INTO testtree VALUES ('Manchester',NULL,'Rusholme',NULL,'Claremont Rd','DestinationPane.jsp?street=CLAREMONT+RD');
INSERT INTO testtree VALUES ('Manchester',NULL,'Rusholme',NULL,'Great Western St','DestinationPane.jsp?street=GREAT WESTERN STREET');
INSERT INTO testtree VALUES ('Manchester',NULL,'Victoria Park',NULL,'Oxford Place','DestinationPane.jsp?street=OXFORD+PLACE');
INSERT INTO testtree VALUES ('Manchester',NULL,'Didsbury','DestinationPane.jsp?area=DIDSBURY',NULL,NULL);
INSERT INTO testtree VALUES ('London','DestinationPane.jsp?city=LONDON',NULL,NULL,NULL,NULL);

