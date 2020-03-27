drop table Instrument if exists;
drop sequence if exists INSTRUMENT_SEQ;
create sequence INSTRUMENT_SEQ start with 1 increment by 50;
create table Instrument (
        instrumentType varchar(31) not null,
        id bigint not null,
        amountInOriginalCurrency decimal(19,2),
        brokerLei varchar(255),
        counterpartyLei varchar(255),
        dealDate timestamp,
        originalCurrency varchar(255),
        valueDate timestamp,
        isin varchar(255),
        quantity bigint,
        maturityDate timestamp,
        tracker varchar(255),
        direction varchar(255),
        strikeAmount decimal(19,2),
        primary key (id)
);
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '254900LAW6SKNVPBBN21', '969500CHL179N00GX059', PARSEDATETIME('17-09-2017','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('17-09-2017','yyyy-dd-mm','en'), 'BE7261065565', NULL, 5445, PARSEDATETIME('09/01/2020','mm/dd/yyyy','en'), 539926.2, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '724500PEZUPUYR4BRB49', '959800ZEL3D4ZSNB4687', PARSEDATETIME('08-09-2010','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('08-09-2010','yyyy-dd-mm','en'), 'BE7261727404', NULL, 9033, PARSEDATETIME('11/12/2020','mm/dd/yyyy','en'), 898783.5, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '9598008Z7MNQCDK3DN13', '9845008979K65B620X11', PARSEDATETIME('25-09-2012','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('25-09-2012','yyyy-dd-mm','en'), 'BE7281695649', NULL, 1810, PARSEDATETIME('02/03/2020','mm/dd/yyyy','en'), 183262.5, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '2138005W55XEB2FY4L58', '959800QS1GU1LXNVQF70', PARSEDATETIME('28-01-2011','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('28-01-2011','yyyy-dd-mm','en'), 'BEAR00540375', NULL, 1108, PARSEDATETIME('03/25/2019','mm/dd/yyyy','en'), 110800.0, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '959800FCCUKG9UAU4169', '959800BFHSLJ8ZYH8T96', PARSEDATETIME('11-12-2008','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('11-12-2008','yyyy-dd-mm','en'), 'BEB157706792', NULL, 3739, PARSEDATETIME('10/01/2021','mm/dd/yyyy','en'), 360589.16, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '959800CGAZSNGKCH9416', '52990097DKT8ZDY4U953', PARSEDATETIME('26-06-2010','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('26-06-2010','yyyy-dd-mm','en'), 'BE7261697813', NULL, 6315, PARSEDATETIME('02/27/2020','mm/dd/yyyy','en'), 631500.0, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '254900POY4T52WBRZ178', '254900JQ7G3S5DKETO15', PARSEDATETIME('10-11-2012','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('10-11-2012','yyyy-dd-mm','en'), 'BEAR00547487', NULL, 7434, PARSEDATETIME('06/01/2024','mm/dd/yyyy','en'), 745630.2, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '52990092IS1DY0KP9Z41', '54930044EWPZ74551F13', PARSEDATETIME('18-02-2012','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('18-02-2012','yyyy-dd-mm','en'), 'BE7260986274', NULL, 1645, PARSEDATETIME('06/18/2019','mm/dd/yyyy','en'), 148461.25, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '815600D2FCCB189AD389', '549300QWO4UFVOCHJ567', PARSEDATETIME('30-06-2016','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('30-06-2016','yyyy-dd-mm','en'), 'BEB157554507', NULL, 3959, PARSEDATETIME('02/16/2020','mm/dd/yyyy','en'), 419654.0, NULL, NULL  );
INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, 'B', '335800DV2WKUAMUTYL21', '254900Z5WUXGPYH1WS92', PARSEDATETIME('10-07-2013','yyyy-dd-mm','en'), 'EUR', PARSEDATETIME('10-07-2013','yyyy-dd-mm','en'), 'BEC0000AIP48', NULL, 4969, PARSEDATETIME('09/22/2020','mm/dd/yyyy','en'), 539235.88, NULL, NULL  );
