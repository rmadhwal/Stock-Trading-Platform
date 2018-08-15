drop table USERS if exists;

create table USERS(
	ID bigint(20) NOT NULL AUTO_INCREMENT,
	FIRSTNAME varchar NOT NULL,
	LASTNAME varchar NOT NULL,
	PASSWORD varchar NOT NULL,
	PHONE bigint(20) NOT NULL,
	EMAIL varchar NOT NULL,
	ROLE varchar NOT NULL,
	PRIMARY KEY (ID)
	);

drop table ORDERS if exists;

create table ORDERS(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  ORDERTYPE varchar NOT NULL,
  STATUS varchar NOT NULL,
  SIDE varchar NOT NULL,
  TIMESTAMP varchar NOT NULL,
  FILLEDQUANTITY bigint(20) NOT NULL,
  PRICE float NOT NULL,
  QUANTITY bigint(20) NOT NULL,
  TICKERSYMBOL varchar NOT NULL,
  OWNERID bigint(20) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (OWNERID) REFERENCES USERS(ID)
);


drop table SECTORS if exists;

create table SECTORS(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  NAME varchar NOT NULL,
  DESCRIPTION varchar NOT NULL,
  PRIMARY KEY (ID)
);

drop table COMPANIES if exists;

create table COMPANIES(
  SYMBOL varchar NOT NULL,
  NAME varchar NOT NULL,
  SECTORID bigint(20),
  PRIMARY KEY (SYMBOL),
  FOREIGN KEY (SECTORID) REFERENCES SECTORS(ID)
);
