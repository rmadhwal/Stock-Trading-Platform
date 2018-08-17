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
  PRICE float,
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

drop table SECTORS if exists;

create table SECTORS(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  NAME varchar NOT NULL,
  DESCRIPTION varchar NOT NULL,
  PRIMARY KEY (ID)
);

drop table TRANSACTIONS if exists;

create table TRANSACTIONS(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  BUYORDERID bigint(20),
  SELLORDERID bigint(20),
  QUANTITY bigint(20),
  PRICE float,
  TIMESTAMP varchar,
  PRIMARY KEY (ID),
  FOREIGN KEY (BUYORDERID) REFERENCES ORDERS(ID),
  FOREIGN KEY (SELLORDERID) REFERENCES ORDERS(ID)
);

drop table LOGS if exists;

create table LOGS(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  EVENTTYPE varchar NOT NULL,
  BUYSIDEORDEROWNERID bigint(20),
  SELLSIDEORDEROWNERID bigint(20),
  QUANTITY bigint(20),
  PRICE float,
  BUYORDERTYPE varchar,
  SELLORDERTYPE varchar,
  TICKERSYMBOL varchar NOT NULL,
  TIMESTAMP varchar NOT NULL
);