-- drop the existing database
drop database mysql;

-- create the test user
create user test password 'test';

-- create the database
create database mysql owner test;
