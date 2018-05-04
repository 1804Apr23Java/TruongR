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
--Task – Insert two new records into Employee table
--Task – Insert two new records into Customer table





--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”    




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





--
--SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.

CREATE OR REPLACE FUNCTION RETURN_CURRENT_TIME
RETURN DATE IS C_TIME DATE;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO C_TIME FROM DUAL;
    RETURN C_TIME;
END RETURN_CURRENT_TIME;

--TESTING
DECLARE 
    C_TIME DATE; 
BEGIN 
    C_TIME := RETURN_CURRENT_TIME(); 
    dbms_output.put_line('AVERAGE INVOICELINE PRICE: ' || C_TIME); 
END; 
/

--Task – Create a function that returns the length of name in MEDIATYPE table

CREATE OR REPLACE FUNCTION LONGEST_NAME_LENGTH
RETURN VARCHAR2 IS LONGEST_NAME MEDIATYPE.NAME%TYPE;
BEGIN
    SELECT MAX(LENGTH(NAME)) INTO LONGEST_NAME FROM MEDIATYPE; 
    RETURN LONGEST_NAME;
END LONGEST_NAME_LENGTH;

--TESTING
DECLARE 
    N_LENGTH MEDIATYPE.NAME%TYPE; 
BEGIN 
    N_LENGTH := LONGEST_NAME_LENGTH(); 
    dbms_output.put_line('LONGEST MEDIATYPE NAME: ' || N_LENGTH); 
END; 
/

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 

SELECT AVG(TOTAL) FROM INVOICE;

--Task – Create a function that returns the most expensive track

SELECT MAX(UNITPRICE) FROM TRACK;

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

CREATE OR REPLACE FUNCTION AVG_INVOICELINE_PRICE
RETURN NUMBER IS AVERAGE NUMBER;
BEGIN
    SELECT AVG(UNITPRICE) INTO AVERAGE 
        FROM INVOICELINE;
    RETURN AVERAGE;
END AVG_INVOICELINE_PRICE;

--TESTING
DECLARE 
    AVERAGE NUMBER; 
BEGIN 
    AVERAGE := AVG_INVOICELINE_PRICE(); 
    dbms_output.put_line('AVERAGE INVOICELINE PRICE: ' || AVERAGE); 
END; 
/


--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.

--SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= TO_DATE('1969-01-01', 'yyyy-mm-dd');

CREATE OR REPLACE FUNCTION EMPLOYEES_BORN_AFTER_1968
RETURN CURSOR;
BEGIN
    CURSOR E_CURSOR IS SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= TO_DATE('1969-01-01', 'yyyy-mm-dd');
    RETURN E_CURSOR;
END EMPLOYEES_BORN_AFTER_1968;


--4.0 Stored Procedures
-- In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.





--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.





--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
--Task – Create a stored procedure that returns the managers of an employee.





--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
--




--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
-- 
--





--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.





--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
--





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
ON artist.artistid = ALBUM.ARTISTID;



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
