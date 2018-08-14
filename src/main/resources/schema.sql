drop table USERS if exists;

create table USERS(
	ID bigint(20) NOT NULL AUTO_INCREMENT,
	FIRSTNAME varchar NOT NULL,
	LASTNAME varchar NOT NULL,
	EMAIL varchar NOT NULL);