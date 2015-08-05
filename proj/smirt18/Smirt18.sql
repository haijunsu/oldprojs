drop table SMIRT18.T_USERS;
drop table SMIRT18.T_MENUS;
drop table SMIRT18.T_QUERYS;
drop table SMIRT18.T_GROUPS;
create table SMIRT18.T_USERS (
   SERIAL NUMERIC(19,0) IDENTITY NOT NULL,
   USERID VARCHAR(30) not null unique,
   PASS VARCHAR(32) null,
   USERNAME VARCHAR(100) null,
   primary key (SERIAL)
);
create table SMIRT18.T_MENUS (
   SERIAL NUMERIC(19,0) IDENTITY NOT NULL,
   MENUID VARCHAR(10) not null unique,
   PARENTID VARCHAR(10) null,
   PURVIEW INT null,
   HTTPURL VARCHAR(255) null,
   primary key (SERIAL)
);
create table SMIRT18.T_QUERYS (
   SERIAL NUMERIC(19,0) IDENTITY NOT NULL,
   QUERYNAME VARCHAR(255) not null,
   QUERYSQL VARCHAR(8000) null,
   primary key (SERIAL)
);
create table SMIRT18.T_GROUPS (
   SERIAL NUMERIC(19,0) IDENTITY NOT NULL,
   USERID VARCHAR(30) not null,
   GROUPNAME VARCHAR(30) null,
   primary key (SERIAL)
);
