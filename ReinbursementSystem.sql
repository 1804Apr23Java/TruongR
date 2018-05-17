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

DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE (
    EMPLOYEEID NUMBER NOT NULL,
    USERNAME VARCHAR2(40),
    PASSWORD VARCHAR2(40),
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMPLOYEEID)
);
/

DROP TABLE MANAGER;
CREATE TABLE MANAGER (
    MANAGERID NUMBER NOT NULL,
    EMPLOYEEID NUMBER NOT NULL,
    CONSTRAINT PK_MANAGER PRIMARY KEY (MANAGERID)
);
/

DROP TABLE REQUEST;
CREATE TABLE REQUEST (
    REQUESTID NUMBER NOT NULL,
    EMPLOYEEID NUMBER,
    IMAGELINK VARCHAR(2000),
    STATUS NUMBER,
    MANAGERID NUMBER,
    CONSTRAINT PK_REQUEST PRIMARY KEY (REQUESTID)
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
    Insert
**************************************************************************************/

INSERT INTO BANKCLIENT(USERNAME,PASSWORD) VALUES ('Jordan123','Marvel2018!');

--select * from bankclient;

/**************************************************************************************************
Insert into Account
***************************************************************************************************/

INSERT INTO ACCOUNT(ACCOUNTBALANCE,BANKCLIENTID) VALUES(500.00,2);


--SELECT * FROM ACCOUNT ORDER BY BANKCLIENTID;

/********************************************************************************
INSERT INTO ADMIN
********************************************************************************/

INSERT INTO ADMIN(ADMINUSERNAME,ADMINPASSWORD) VALUES ('Robert','Welcome@123');
INSERT INTO ADMIN(ADMINUSERNAME,ADMINPASSWORD) VALUES ('Shivam','Password@123');

CREATE OR REPLACE PROCEDURE TX_INSERT(BKID IN BANKCLIENT.BANKCLIENTID%TYPE, MSG IN TRANSACTIONS.MESSAGE%TYPE) AS
BEGIN
INSERT INTO TRANSACTIONS VALUES (BKID, CURRENT_TIMESTAMP, MSG);
END;
/

COMMIT;

BEGIN
TX_INSERT(2, 'testmsg');
END;

--select * from admin;select * from account where ACCOUNT.BANKCLIENTID =1;

--select * from account LEFT JOIN BANKCLIENT ON ACCOUNT.BANKCLIENTID=BANKCLIENT.BANKCLIENTID ORDER BY ACCOUNTID;

DELETE FROM TRANSACTIONS WHERE BANKCLIENTID = 2;
