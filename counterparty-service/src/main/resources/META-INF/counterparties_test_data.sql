drop table Counterparty if exists;
create table Counterparty (
    lei varchar(255) not null,
    city varchar(255),
    country varchar(255),
    firstAddressLine varchar(255),
    postalCode varchar(255),
    region varchar(255),
    name varchar(255),
    category varchar(255),
    jurisdiction varchar(255),
    lastUpdated timestamp,
    legalFormCode varchar(255),
    nextRenewalDate timestamp,
    registrationAuthorityEntityID varchar(255),
    registrationAuthorityID varchar(255),
    registrationDate timestamp,
    registrationStatus varchar(255),
    status integer,
    primary key (lei)
);
TRUNCATE TABLE COUNTERPARTY; 
INSERT INTO COUNTERPARTY (lei, name, firstAddressLine, city, region, country, postalCode, registrationAuthorityID, registrationAuthorityEntityID,jurisdiction, category, legalFormCode,  status, registrationDate, lastUpdated, registrationStatus, nextRenewalDate) VALUES ('353800DERDIT6K33ZH46','三井住友信託銀行信託/00012924','1-4-1　Marunouchi','Tokyo Chiyoda-ku','','JP','100-8233','RA888888','12924','JP','','','0','2018-12-27','2018-12-27','ISSUED','2019-12-27');
INSERT INTO COUNTERPARTY (lei, name, firstAddressLine, city, region, country, postalCode, registrationAuthorityID, registrationAuthorityEntityID,jurisdiction, category, legalFormCode,  status, registrationDate, lastUpdated, registrationStatus, nextRenewalDate) VALUES ('984500C2EEUEB4A0C629','Niclas Engborg Holding AB','Ekstigen 14','Solna','SE-AB','SE','169 34','RA000544','556786-4045','SE','','XJHM','0','2018-12-27','2018-12-27','ISSUED','2019-12-27');
INSERT INTO COUNTERPARTY (lei, name, firstAddressLine, city, region, country, postalCode, registrationAuthorityID, registrationAuthorityEntityID,jurisdiction, category, legalFormCode,  status, registrationDate, lastUpdated, registrationStatus, nextRenewalDate) VALUES ('9845002DQDE9D546D910','INTERDAYS, LDA','Av. Miguel Dantas Edifício Status Loja Nº 40','Valença','','PT','4930-678','RA000487','508715709','PT','','USOG','0','2018-12-27','2018-12-27','ISSUED','2019-12-27');
INSERT INTO COUNTERPARTY (lei, name, firstAddressLine, city, region, country, postalCode, registrationAuthorityID, registrationAuthorityEntityID,jurisdiction, category, legalFormCode,  status, registrationDate, lastUpdated, registrationStatus, nextRenewalDate) VALUES ('254900DR0LRA0YI2XS82','Midwest Retail Mezz II LLC','c/o Corporation Service Center','Wilmington','US-DE','US','19808','RA000602','7137424','US-DE','','9999','0','2018-12-26','2018-12-26','ISSUED','2019-12-26');
INSERT INTO COUNTERPARTY (lei, name, firstAddressLine, city, region, country, postalCode, registrationAuthorityID, registrationAuthorityEntityID,jurisdiction, category, legalFormCode,  status, registrationDate, lastUpdated, registrationStatus, nextRenewalDate) VALUES ('8156003FD83099AA0794','UBM MANAGEMENT SERVICE S.R.L.','VIA ULISSE ALDROVANDI, 7','MILANO','IT-MI','IT','20129','RA000407','09746830158','IT','','OV32','0','2018-12-26','2018-12-26','ISSUED','2019-12-26');
