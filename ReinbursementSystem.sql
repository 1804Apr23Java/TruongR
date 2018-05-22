/*
DROP USER rtrs CASCADE;

CREATE USER rtrs
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to rtrs;
GRANT resource to rtrs;
GRANT create session TO rtrs;
GRANT create table TO rtrs;
GRANT create view TO rtrs;


conn rtrs/p4ssw0rd

*/

/*******************************************************************************
   Create Tables
********************************************************************************/

DROP TABLE REQUEST;
CREATE TABLE REQUEST (
    REQUESTID NUMBER NOT NULL,
    EMPLOYEEID NUMBER NOT NULL,
    AMOUNT NUMBER(38,2) NOT NULL,
    IMAGELINK VARCHAR(2000),
    STATUS NUMBER DEFAULT 0,
    MANAGERID NUMBER,
    CONSTRAINT PK_REQUEST PRIMARY KEY (REQUESTID)
);
/

DROP TABLE MANAGER;
CREATE TABLE MANAGER (
    MANAGERID NUMBER NOT NULL,
    EMPLOYEEID NUMBER NOT NULL,
    CONSTRAINT PK_MANAGER PRIMARY KEY (MANAGERID)
);
/

DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE (
    EMPLOYEEID NUMBER NOT NULL,
    USERNAME VARCHAR2(40) NOT NULL,
    PASSWORD VARCHAR2(40) NOT NULL,
    FIRSTNAME VARCHAR2(40) NOT NULL,
    LASTNAME VARCHAR2(40) NOT NULL,
    ADDRESS VARCHAR2(100) NOT NULL,
    CITY VARCHAR2(40) NOT NULL,
    STATE VARCHAR2(40) NOT NULL,
    ZIP VARCHAR2(40) NOT NULL,
    PHONE VARCHAR2(40) NOT NULL,
    EMAIL VARCHAR2(40) NOT NULL,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMPLOYEEID),
    CONSTRAINT UQ_USERNAME UNIQUE (USERNAME)
);
/



/************************************************************************************
    Foreign Key
*************************************************************************************/

ALTER TABLE REQUEST ADD CONSTRAINT FK_EMPLOYEE_REQUEST
    FOREIGN KEY (EMPLOYEEID) REFERENCES EMPLOYEE(EMPLOYEEID);
        
ALTER TABLE REQUEST ADD CONSTRAINT FK_MANAGER_REQUEST
    FOREIGN KEY (MANAGERID) REFERENCES MANAGER(MANAGERID);

/************************************************************************************
    Auto-Increments
*************************************************************************************/
DROP SEQUENCE SQ_EMPLOYEE_PK;
DROP SEQUENCE SQ_MANAGER_PK;
DROP SEQUENCE SQ_REQUEST_PK;

CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_MANAGER_PK
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_REQUEST_PK
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TR_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMPLOYEEID FROM DUAL;    
END;
/

CREATE OR REPLACE TRIGGER TR_MANAGER
BEFORE INSERT ON MANAGER
FOR EACH ROW
BEGIN
    SELECT SQ_MANAGER_PK.NEXTVAL INTO :NEW.MANAGERID FROM DUAL;    
END;
/

CREATE OR REPLACE TRIGGER TR_REQUEST
BEFORE INSERT ON REQUEST
FOR EACH ROW
BEGIN
    SELECT SQ_REQUEST_PK.NEXTVAL INTO :NEW.REQUESTID FROM DUAL;    
END;
/

        
/*************************************************************************************
Insert Into Employee
**************************************************************************************/

INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob1', 'pass1', 'Bob', 'Oneson', '1111 Bob Street', 'Bobsville', 'AZ', '80801', '808-808-8081', 'bob8081@hotmail.com');
INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob2', 'pass2', 'Tbob', 'Twoson', '222 Bob Street', 'Bobscity', 'CO', '80802', '808-808-8082', 'bob8082@hotmail.com');
INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob3', 'pass3', 'Thrbob', 'Threeson', '33 Bob Street', 'Bobstown', 'FL', '80803', '808-808-8083', 'bob8083@hotmail.com');
INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob4', 'pass4', 'Fbob', 'Fourson', '44444 Bob Street', 'Bobstopia', 'NV', '80804', '808-808-8084', 'bob8084@hotmail.com');
INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob5', 'pass5', 'Fibob', 'Fiveson', '5 Bob Street', 'Bobscitadel', 'OR', '80805', '808-808-8085', 'bob8085@hotmail.com');
INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) VALUES
    ('Bob6', 'pass6', 'Sbob', 'Sixson', '6666 Bob Street', 'Bobsterrace', 'MD', '80806', '808-808-8086', 'bob8086@hotmail.com');

--select * from bankclient;

/**************************************************************************************************
Insert into Manager
***************************************************************************************************/

INSERT INTO MANAGER (EMPLOYEEID) VALUES (2);
INSERT INTO MANAGER (EMPLOYEEID) VALUES (5);

--SELECT * FROM ACCOUNT ORDER BY BANKCLIENTID;

/********************************************************************************
Insert into Request
********************************************************************************/

INSERT INTO REQUEST (EMPLOYEEID, AMOUNT, IMAGELINK) VALUES (3, 54.99, 'www.validhyperlink.com/image.png');

COMMIT;