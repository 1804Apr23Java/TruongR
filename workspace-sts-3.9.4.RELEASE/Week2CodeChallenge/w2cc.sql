
DROP USER w2cc CASCADE;

CREATE USER w2cc
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to w2cc;
GRANT resource to w2cc;
GRANT create session TO w2cc;
GRANT create table TO w2cc;
GRANT create view TO banking2;


conn w2cc/p4ssw0rd


/*******************************************************************************
   Create Tables
********************************************************************************/
DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE (
    EMPLOYEE_ID INTEGER,
    EMP_FIRSTNAME VARCHAR2(50),
    EMP_LASTNAME VARCHAR2(50),
    DEPARTMENT_ID INTEGER,
    SALARY NUMBER(20,2),
    EMP_EMAIL VARCHAR2(50),
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMPLOYEE_ID)
);
/

DROP TABLE DEPARTMENT;
CREATE TABLE DEPARTMENT (
    DEPARTMENT_ID INTEGER,
    DEPARTMENT_NAME VARCHAR2(50),
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPARTMENT_ID)
);
/

/************************************************************************************
    Foreign Key
*************************************************************************************/

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_DEPARTMENT 
    FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID);
    
/************************************************************************************
    Auto-Increments
*************************************************************************************/
DROP SEQUENCE SQ_EMPLOYEE_PK;
DROP SEQUENCE SQ_DEPARTMENT_PK;

CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_DEPARTMENT_PK
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TR_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMPLOYEE_ID FROM DUAL;    
END;
/

CREATE OR REPLACE TRIGGER TR_DEPARTMENT
BEFORE INSERT ON DEPARTMENT
FOR EACH ROW
BEGIN
    SELECT SQ_DEPARTMENT_PK.NEXTVAL INTO :NEW.DEPARTMENT_ID FROM DUAL;
END;
/

        
/*************************************************************************************
    Insert
**************************************************************************************/

INSERT INTO DEPARTMENT(DEPARTMENT_NAME) VALUES ('COMPUTER SCIENCE');
INSERT INTO DEPARTMENT(DEPARTMENT_NAME) VALUES ('MATHEMATICS');
INSERT INTO DEPARTMENT(DEPARTMENT_NAME) VALUES ('ART HISTORY');

INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('John', 'Johnson', 1, 40000, 'jjohnson@abc.edu');
INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('Tom', 'Thompson', 1, 34000, 'tthompson@abc.edu');
INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('Shannon', 'Shanson', 1, 35000, 'sshanson@abc.edu');
INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('Marsha', 'Marshson', 2, 22000, 'mmarshson@abc.edu');
INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('Derek', 'Derkson', 3, 45000, 'dderkson@abc.edu');
INSERT INTO EMPLOYEE(EMP_FIRSTNAME, EMP_LASTNAME, DEPARTMENT_ID, SALARY, EMP_EMAIL) VALUES
            ('Hanlon', 'Hanlonson', 3, 55000, 'hhanlonson@abc.edu');

COMMIT;

CREATE OR REPLACE PROCEDURE SP_GIVE_RAISE(DEPT_ID IN INTEGER, AVG_SALARY OUT EMPLOYEE.SALARY%TYPE, VALID_ID OUT INTEGER) AS
DEPT_VALID_CHECK INTEGER;

BEGIN
    SELECT COUNT (*) INTO DEPT_VALID_CHECK FROM DEPARTMENT WHERE DEPARTMENT_ID = DEPT_ID;
    IF (DEPT_VALID_CHECK = 0) THEN
        AVG_SALARY := 0;
        VALID_ID := 0;
    ELSE
        UPDATE EMPLOYEE SET SALARY = SALARY * 1.1 WHERE EMPLOYEE.DEPARTMENT_ID = DEPT_ID;
        SELECT AVG(SALARY) INTO AVG_SALARY FROM EMPLOYEE WHERE EMPLOYEE.DEPARTMENT_ID = DEPT_ID;
        VALID_ID := 1;
        COMMIT;
    END IF;
END;


/* DECLARE
VALID_ID INTEGER;
AVG_SALARY EMPLOYEE.SALARY%TYPE;
BEGIN
    SP_GIVE_RAISE(0, AVG_SALARY, VALID_ID);
    IF (VALID_ID = 1) THEN
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(AVG_SALARY));
    ELSE
        DBMS_OUTPUT.PUT_LINE('INVALID DEPT');
    END IF;
END; */

/*
SELECT DEPARTMENT.DEPARTMENT_NAME, AVG(EMPLOYEE.SALARY) AS AVG_SALARY FROM
	EMPLOYEE INNER JOIN DEPARTMENT ON EMPLOYEE.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID
	GROUP BY EMPLOYEE.DEPARTMENT_ID, DEPARTMENT.DEPARTMENT_NAME; */