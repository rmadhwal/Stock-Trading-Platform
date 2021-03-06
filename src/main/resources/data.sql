insert into users(firstname, lastname, password, phone, email, role) values('Siva', 'Lim', '123456', '88776644','siva.lim@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('XYZ', 'Tan', '123456', '99887766','xyz.tan@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('Rohan','Wang','123456','77889988', 'rohan.wang@gmail.com','ADMIN')
insert into users(id, firstname, lastname, password, phone, email, role) values(0, 'Rohaan','Wang','123456','77889988', 'rohan.wang@gmail.com','ADMIN')
insert into users(firstname, lastname, password, phone, email, role) values('Vincent','Wong','123456','77889988', 'vincent.wong@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('Timothy','Tan','123456','99008877', 'timothy.tan@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('Valerie','Lim','123456','88776644', 'valerie.lim@gmail.com','TRADER')
insert into users(firstname, lastname, password, phone, email, role) values('Michelle','Chong','123456','88776644', 'michelle.chong@gmail.com','TRADER')
insert into sectors(id, name, description) values(0, 'IT', 'the technology sector')


insert into sectors(id, name, description) values(1, 'sector with companies', 'sector with companies')
insert into sectors(id, name, description) values(2, 'sector without any companies', 'sector without any companies')
insert into companies(symbol, name, sectorid) values('ATH','ATHENE','1')
insert into companies(symbol, name, sectorid) values('FB','FACEBOOK','1')

insert into sectors(id, name, description) values(44, 'test companies', 'test companies')
insert into users(id, firstname, lastname, password, phone, email, role) values(999,'TEST','TEST','123456','77889988', 'test@gmail.com','TRADER')
insert into companies(symbol, name, sectorid) values('COMPANY_WITH_ORDERS','COMPANY_WITH_ORDERS',44)
insert into companies(symbol, name, sectorid) values('COMPANY_WITHOUT_ORDERS','COMPANY_WITHOUT_ORDERS',44)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (11, 'MARKET','OPEN','BUY','04-Jul-2018 12:08:56.235',50,19.99,100,'COMPANY_WITH_ORDERS',999)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (12, 'MARKET','OPEN','BUY','04-Jul-2018 12:08:56.235',40,19.99,50,'COMPANY_WITH_ORDERS',999)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (13,'LIMIT','FULFILLED','SELL','05-Jul-2018 12:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',2)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (14,'LIMIT','FULFILLED','SELL','05-Jul-2018 13:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',2)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (15,'LIMIT','CANCELLED','BUY','08-Jul-2018 15:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',2)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (16,'LIMIT','FULFILLED','SELL','08-Jul-2018 18:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',1)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (17,'LIMIT','FULFILLED','SELL','10-Jul-2018 18:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',6)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (18,'LIMIT','CANCELLED','BUY','10-Jul-2018 18:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',5)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (19,'LIMIT','OPEN','BUY','10-Jul-2018 18:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',5)
insert into orders(id, ordertype,status,side,timestamp,filledQuantity,price,quantity,tickerSymbol,ownerid) values (20,'LIMIT','OPEN','SELL','10-Jul-2018 18:08:56.235',50,19.99,50,'COMPANY_WITH_ORDERS2',4)

insert into companies(symbol, name, sectorid) values('COMPANY_WITH_ORDERS_2','COMPANY_WITHOUT_ORDERS_2',44)

insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (11, 13, 50, 19.99, '04-Jul-2018 12:08:56.236')
insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (12, 14, 50, 111.99, '05-Jul-2018 12:08:56.236')

insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (12, 13, 50, 19.99, '04-Jul-2018 12:08:56.236')
insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (12, 13, 50, 120.99, '06-Jul-2018 12:08:56.236')

insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (15, 17, 50, 111.99, '05-Jul-2018 12:08:56.236')
insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (16, 18, 50, 120.99, '06-Jul-2018 12:08:56.236')
insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) values (19, 20, 50, 111.99, '07-Jul-2018 12:08:56.236')

insert into logs(eventtype, quantity, price, tickersymbol, timestamp) values('PLACEMENT', '100','1.23','ATH','10-Jul-2018 18:08:56.235')


