drop table USERS if exists;

create table USERS(
	ID bigint(20) NOT NULL AUTO_INCREMENT,
	NAME varchar NOT NULL,
	EMAIL varchar NOT NULL);