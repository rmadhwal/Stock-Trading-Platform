insert into users(firstname, lastname, password, phone, email, role) values('Siva', 'Lim', '123456', '88776644','siva.lim@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('XYZ', 'Tan', '123456', '99887766','xyz.tan@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('Rohan','Wang','123456','77889988', 'rohan.wang@gmail.com','ADMIN')
insert into users(id, firstname, lastname, password, phone, email, role) values(0, 'Rohaan','Wang','123456','77889988', 'rohan.wang@gmail.com','ADMIN')
insert into companies(symbol, name, sectorid) values('ATH','ATHENE','1')
insert into companies(symbol, name, sectorid) values('FB','FACEBOOK','1')
insert into sectors(id, name, description) values(0, 'IT', 'the technology sector')


insert into sectors(id, name, description) values(44, 'test companies', 'test companies')
insert into users(id, firstname, lastname, password, phone, email, role) values(999,'TEST','TEST','123456','77889988', 'test@gmail.com','TRADER')
insert into companies(symbol, name, sectorid) values('COMPANY_WITH_ORDERS','COMPANY_WITH_ORDERS',44)
insert into companies(symbol, name, sectorid) values('COMPANY_WITHOUT_ORDERS','COMPANY_WITHOUT_ORDERS',44)
insert into orders(id,ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (990,'MARKET','OPEN','BUY','04-Jul-2018 12:08:56.235',0,19.99,100,'COMPANY_WITH_ORDERS',999)


insert into sectors(id, name, description) values(1, 'sector with companies', 'sector with companies')
insert into sectors(id, name, description) values(2, 'sector without any companies', 'sector without any companies')