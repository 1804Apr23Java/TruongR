--SQL Lab
--
--Setting up Oracle Chinook
--In this section you will begin the process of working with the Oracle Chinook database
--(https://github.com/lerocha/chinook-database) 
--Task – Open the Chinook_Oracle.sql file and execute the scripts within.

--2     SQL Queries
--In this section you will be performing various queries against the Oracle Chinook database.
--2.1 SELECT
--Task – Select all records from the Employee table.

SELECT * FROM EMPLOYEE;

--Task – Select all records from the Employee table where last name is King.

SELECT * FROM EMPLOYEE WHERE LASTNAME='King';

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.

SELECT * FROM EMPLOYEE WHERE FIRSTNAME='Andrew' AND REPORTSTO IS NULL;

--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.

SELECT * FROM ALBUM ORDER BY TITLE DESC;

--Task – Select first name from Customer and sort result set in ascending order by city

SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table 

INSERT INTO GENRE VALUES (28, 'Bad Metal');
INSERT INTO GENRE VALUES (29, 'Rilly Bad Metal');

--Task – Insert two new records into Employee table

INSERT INTO EMPLOYEE VALUES (20, 'John', 'Smith', 'Slacker', 2, TO_DATE('1994-04-04', 'YYYY-MM-DD'), TO_DATE('2004-04-04', 'YYYY-MM-DD'),
                    '1234 Simmons Ave', 'Los Angeles', 'CA', 'USA', '41231', '+1 (999) 999-9999', '+1 (999) 999-9999', 'js@js.org');
INSERT INTO EMPLOYEE VALUES (21, 'Jane', 'Smith', 'Slackers Wife', 2, TO_DATE('1995-05-05', 'YYYY-MM-DD'), TO_DATE('2005-05-05', 'YYYY-MM-DD'),
                    '1234 Simmons Ave', 'Los Angeles', 'CA', 'USA', '41231', '+1 (999) 999-9999', '+1 (999) 999-9999', 'js2@js.org');

--Task – Insert two new records into Customer table

INSERT INTO CUSTOMER VALUES (75, 'Janice', 'Janison', 'Tesla', '5678 Burgundy Rd', 'Los Angeles', 'CA', 'USA', '41231', '+1 (999) 999-9999', '+1 (999) 999-9999', 'js2@js.org', 2);
INSERT INTO CUSTOMER VALUES (76, 'George', 'Georgeson', 'Google', '5678 Burgundy Rd', 'Los Angeles', 'CA', 'USA', '41231', '+1 (999) 999-9999', '+1 (999) 999-9999', 'js2@js.org', 2);





--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter

UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”    

UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 

SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004

SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE('2003-06-01', 'yyyy-mm-dd') AND TO_DATE('2004-03-01', 'yyyy-mm-dd');

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).


DELETE FROM INVOICELINE WHERE INVOICELINEID IN (SELECT INVOICELINEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
                        INNER JOIN INVOICELINE ON INVOICE.INVOICEID = INVOICELINE.INVOICEID
                        WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');

DELETE FROM INVOICE WHERE INVOICEID IN (SELECT INVOICEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
                        WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');

DELETE FROM CUSTOMER WHERE LASTNAME = 'Walter' AND FIRSTNAME = 'Robert';



--
--SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.

CREATE OR REPLACE FUNCTION RETURN_CURRENT_TIME
RETURN TIMESTAMP IS C_TIME TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO C_TIME FROM DUAL;
    RETURN C_TIME;
END RETURN_CURRENT_TIME;

----TESTING
--DECLARE 
--    C_TIME TIMESTAMP; 
--BEGIN 
--    C_TIME := RETURN_CURRENT_TIME(); 
--    dbms_output.put_line('CURRENT TIME: ' || C_TIME); 
--END; 
--/

--Task – Create a function that returns the length of name in MEDIATYPE table

CREATE OR REPLACE FUNCTION LONGEST_NAME_LENGTH
RETURN VARCHAR2 IS LONGEST_NAME MEDIATYPE.NAME%TYPE;
BEGIN
    SELECT MAX(LENGTH(NAME)) INTO LONGEST_NAME FROM MEDIATYPE; 
    RETURN LONGEST_NAME;
END LONGEST_NAME_LENGTH;

----TESTING
--DECLARE 
--    N_LENGTH MEDIATYPE.NAME%TYPE; 
--BEGIN 
--    N_LENGTH := LONGEST_NAME_LENGTH(); 
--    dbms_output.put_line('LONGEST MEDIATYPE NAME: ' || N_LENGTH); 
--END; 
--/

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 

CREATE OR REPLACE FUNCTION AVG_INVOICE_TOTAL
RETURN NUMBER IS AVERAGE NUMBER (10,2);
BEGIN
    SELECT AVG(TOTAL) INTO AVERAGE FROM INVOICE;
    RETURN AVERAGE;
END AVG_INVOICE_TOTAL;

--DECLARE 
--    AVG_TOTAL NUMBER; 
--BEGIN 
--    AVG_TOTAL := AVG_INVOICE_TOTAL(); 
--    dbms_output.put_line('AVERAGE INVOICE TOTAL: ' || AVG_TOTAL); 
--END; 
--/

--Task – Create a function that returns the most expensive track

SELECT * FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK) AND ROWNUM = 1;
    

CREATE OR REPLACE FUNCTION MAX_PRICE_TRACK
RETURN TRACK%ROWTYPE IS MAX_TRACK TRACK%ROWTYPE;
BEGIN
    SELECT * INTO MAX_TRACK FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK) AND ROWNUM = 1;
    RETURN MAX_TRACK;
END MAX_PRICE_TRACK;

--DECLARE 
--    MAX_TRACK TRACK%ROWTYPE; 
--BEGIN 
--    MAX_TRACK := MAX_PRICE_TRACK(); 
--    dbms_output.put_line('MOST EXPENSIVE TRACK: ' || MAX_TRACK.NAME || ' AT PRICE: ' || MAX_TRACK.UNITPRICE); 
--END; 
--/
--
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

CREATE OR REPLACE FUNCTION AVG_INVOICELINE_PRICE
RETURN NUMBER IS AVERAGE NUMBER (10,2);
BEGIN
    SELECT AVG(UNITPRICE) INTO AVERAGE
        FROM INVOICELINE;
    RETURN AVERAGE;
END AVG_INVOICELINE_PRICE;

----TESTING
--DECLARE 
--    AVERAGE NUMBER; 
--BEGIN 
--    AVERAGE := AVG_INVOICELINE_PRICE(); 
--    dbms_output.put_line('AVERAGE INVOICELINE PRICE: ' || AVERAGE); 
--END; 
--/


--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.

--SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= TO_DATE('1969-01-01', 'yyyy-mm-dd');

CREATE OR REPLACE FUNCTION EMPLOYEES_BORN_AFTER_1968
RETURN SYS_REFCURSOR AS EMP_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN EMP_CURSOR FOR q'[SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= TO_DATE('1969-01-01', 'yyyy-mm-dd')]';
    RETURN EMP_CURSOR;
END EMPLOYEES_BORN_AFTER_1968;


--TESTING
--DECLARE 
--    EMP_RECORD EMPLOYEE%ROWTYPE;
--    EMP_CURSOR SYS_REFCURSOR;
--BEGIN 
--    EMP_CURSOR := EMPLOYEES_BORN_AFTER_1968();
--    LOOP
--        FETCH EMP_CURSOR INTO EMP_RECORD;
--        EXIT WHEN EMP_CURSOR%NOTFOUND;
--        dbms_output.put_line(EMP_RECORD.FIRSTNAME || ' ' ||EMP_RECORD.LASTNAME || ' ' ||EMP_RECORD.BIRTHDATE); 
--        END LOOP;
--END; 
--/

--4.0 Stored Procedures
-- In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.

CREATE OR REPLACE PROCEDURE SELECT_EMPLOYEE_NAMES (EMP_NAME_CURSOR OUT SYS_REFCURSOR) AS
BEGIN
    OPEN EMP_NAME_CURSOR FOR SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;

--DECLARE 
--    FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE;
--    LASTNAME EMPLOYEE.LASTNAME%TYPE;
--    EMP_NAME_CURSOR SYS_REFCURSOR;
--BEGIN
--    SELECT_EMPLOYEE_NAMES(EMP_NAME_CURSOR);
--    LOOP
--        FETCH EMP_NAME_CURSOR INTO FIRSTNAME, LASTNAME;
--        EXIT WHEN EMP_NAME_CURSOR%NOTFOUND;
--        dbms_output.put_line(FIRSTNAME || ' ' || LASTNAME); 
--        END LOOP;
--END; 
--/

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.

--EMP_ID is used to identify, the other values are the new values to be updated in that entry
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE (EMP_ID IN EMPLOYEE.EMPLOYEEID%TYPE,
                                                EMP_LNAME IN EMPLOYEE.LASTNAME%TYPE,
                                                EMP_FNAME IN EMPLOYEE.FIRSTNAME%TYPE,
                                                EMP_ADDR IN EMPLOYEE.ADDRESS%TYPE,
                                                EMP_CITY IN EMPLOYEE.CITY%TYPE,
                                                EMP_STATE IN EMPLOYEE.STATE%TYPE,
                                                EMP_COUNTRY IN EMPLOYEE.COUNTRY%TYPE,
                                                EMP_ZIP IN EMPLOYEE.POSTALCODE%TYPE,
                                                EMP_PHONE IN EMPLOYEE.PHONE%TYPE,
                                                EMP_FAX IN EMPLOYEE.FAX%TYPE,
                                                EMP_EMAIL IN EMPLOYEE.EMAIL%TYPE) AS
BEGIN
    UPDATE EMPLOYEE 
    SET LASTNAME = EMP_LNAME,
        FIRSTNAME = EMP_FNAME,
        ADDRESS = EMP_ADDR,
        CITY = EMP_CITY,
        STATE = EMP_STATE,
        COUNTRY = EMP_COUNTRY,
        POSTALCODE = EMP_ZIP,
        PHONE = EMP_PHONE,
        FAX = EMP_FAX,
        EMAIL = EMP_EMAIL        
    WHERE EMPLOYEEID = EMP_ID;
END;

--DECLARE  
--BEGIN 
--    UPDATE_EMPLOYEE(11, 'testlast', 'testfirst', '123 whatever st.', 'Richmond', 'VA', 'USA', '99999', '1234567890', '0987654321', 'test@gmail');
--END; 
--/

--Task – Create a stored procedure that returns the managers of an employee.

CREATE OR REPLACE PROCEDURE GET_EMP_MANAGER (EMP_ID IN EMPLOYEE.EMPLOYEEID%TYPE, MANAGER OUT NUMBER) AS
BEGIN
    SELECT EMPLOYEEID INTO MANAGER FROM EMPLOYEE WHERE EMPLOYEEID = (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = EMP_ID);
EXCEPTION
WHEN NO_DATA_FOUND THEN
    dbms_output.put_line('THIS EMPLOYEE HAS NO MANAGER.');
    MANAGER := -1;
END;

--DECLARE
--    MANAGER NUMBER;
--BEGIN 
--    GET_EMP_MANAGER(11, MANAGER);
--    dbms_output.put_line('MANAGER HAS ID: ' || MANAGER);
--END; 
--/


--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.

CREATE OR REPLACE PROCEDURE GET_CUST_INFO (CUST_ID IN CUSTOMER.CUSTOMERID%TYPE, CUST_FN OUT CUSTOMER.FIRSTNAME%TYPE,
                                            CUST_LN OUT CUSTOMER.LASTNAME%TYPE, CUST_COMP OUT CUSTOMER.COMPANY%TYPE) AS
BEGIN
    SELECT FIRSTNAME, LASTNAME, COMPANY INTO CUST_FN, CUST_LN, CUST_COMP FROM CUSTOMER WHERE CUSTOMERID = CUST_ID;
END;

--DECLARE
--    CUST_FN CUSTOMER.FIRSTNAME%TYPE;
--    CUST_LN CUSTOMER.LASTNAME%TYPE;     
--    CUST_COMP CUSTOMER.COMPANY%TYPE;
--BEGIN 
--    GET_CUST_INFO(1, CUST_FN, CUST_LN, CUST_COMP);
--    dbms_output.put_line('CUSTID 1 IS ' || CUST_FN || ' ' || CUST_LN || ' FROM ' || CUST_COMP );
--END; 
--/


--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
-- 
--

CREATE OR REPLACE PROCEDURE DELETE_INVOICE (INV_ID IN INVOICE.INVOICEID%TYPE) AS
BEGIN
    DELETE FROM INVOICELINE WHERE INVOICEID = INV_ID;
    DELETE FROM INVOICE WHERE INVOICEID = INV_ID;
    COMMIT;
END;

--DECLARE
--BEGIN
--    DELETE_INVOICE(1);
--END;


--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.

CREATE OR REPLACE TRIGGER TG_EMP_INSERT AFTER INSERT ON EMPLOYEE FOR EACH ROW
BEGIN
    dbms_output.put_line('NEW EMPLOYEE ADDED');
END;

--INSERT INTO EMPLOYEE VALUES (15, 'ABCD', 'EFGH', 'WHATEVER', '1', TO_DATE('1994-02-04', 'yyyy-mm-dd'), TO_DATE('1994-02-04', 'yyyy-mm-dd'), 
--        'asdf st', 'whateverton', 'CA', 'Canada', '12345','12', '12', '12');
--COMMIT;


--Task – Create an after update trigger on the album table that fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER TG_ALB_INSERT AFTER INSERT ON ALBUM FOR EACH ROW
BEGIN
    dbms_output.put_line('NEW ALBUM ADDED');
END;

--INSERT INTO ALBUM VALUES (351, 'NEWALBUM', 275);
--COMMIT;

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

CREATE OR REPLACE TRIGGER TG_CUST_DELETE AFTER DELETE ON CUSTOMER FOR EACH ROW
BEGIN
    dbms_output.put_line('CUSTOMER DELETED');
END;

--DELETE FROM CUSTOMER WHERE CUSTOMERID=1 CASCADE;
--COMMIT;


--7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.


SELECT CONCAT(CUSTOMER.FIRSTNAME, (CONCAT(' ', CUSTOMER.LASTNAME))) AS CUSTOMERNAME, INVOICE.INVOICEID
FROM CUSTOMER INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER FULL OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;


--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.

SELECT ARTIST.NAME, ALBUM.TITLE
FROM ALBUM RIGHT OUTER JOIN ARTIST
ON ARTIST.ARTISTID = ALBUM.ARTISTID;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.

SELECT * FROM ALBUM CROSS JOIN ARTIST WHERE artist.artistid = ALBUM.ARTISTID ORDER BY ARTIST.NAME ASC;

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.

SELECT * FROM EMPLOYEE E, EMPLOYEE E2
WHERE E.REPORTSTO = E2.EMPLOYEEID;

--
--DUE 9am Monday, May 7
--
--Upload a .sql file containing your answers to your repository with each solution to each part marked with the question number and prompt. 
