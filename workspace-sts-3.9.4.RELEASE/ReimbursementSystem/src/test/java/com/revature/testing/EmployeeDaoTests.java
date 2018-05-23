package com.revature.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.EmployeeField;


@FixMethodOrder(MethodSorters.DEFAULT)
public class EmployeeDaoTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private ManagerDao md = new ManagerDaoImpl();
	private EmployeeDao ed = new EmployeeDaoImpl();

	@Test
	public void getAllEmployeesGetsAllEmployeesFromScript() {
		Employee bob1 = new Employee(1, "Bob1", "pass1", "Bob", "Oneson", "1111 Bob Street", "Bobsville", "AZ", "80801",
				"808-808-8081", "bob8081@hotmail.com");
		Employee bob2 = new Employee(2, "Bob2", "pass2", "Tbob", "Twoson", "222 Bob Street", "Bobscity", "CO", "80802",
				"808-808-8082", "bob8082@hotmail.com");
		Employee bob3 = new Employee(3, "Bob3", "pass3", "Thrbob", "Threeson", "33 Bob Street", "Bobstown", "FL",
				"80803", "808-808-8083", "bob8083@hotmail.com");
		Employee bob4 = new Employee(4, "Bob4", "pass4", "Fbob", "Fourson", "44444 Bob Street", "Bobstopia", "NV",
				"80804", "808-808-8084", "bob8084@hotmail.com");
		Employee bob5 = new Employee(5, "Bob5", "pass5", "Fibob", "Fiveson", "5 Bob Street", "Bobscitadel", "OR",
				"80805", "808-808-8085", "bob8085@hotmail.com");
		Employee bob6 = new Employee(6, "Bob6", "pass6", "Sbob", "Sixson", "6666 Bob Street", "Bobsterrace", "MD",
				"80806", "808-808-8086", "bob8086@hotmail.com");

		List<Employee> allEmployees = md.getAllEmployees();

		assertEquals(bob1, allEmployees.get(0));
		assertEquals(bob2, allEmployees.get(1));
		assertEquals(bob3, allEmployees.get(2));
		assertEquals(bob4, allEmployees.get(3));
		assertEquals(bob5, allEmployees.get(4));
		assertEquals(bob6, allEmployees.get(5));

	}

	@Test
	public void getPendingRequestsWorksForEmployeeWithPendingRequests() {
		
		Timestamp t1 = Timestamp.valueOf(LocalDateTime.of(2000, 1, 1, 1, 1));
		Timestamp t2 = Timestamp.valueOf(LocalDateTime.of(2000, 2, 2, 2, 2));
		Timestamp t3 = Timestamp.valueOf(LocalDateTime.of(2000, 3, 3, 3, 3));
		
		Request r1 = new Request(1, 3, 54.99, t1, "www.validhyperlink.com/image.png");
		Request r2 = new Request(2, 3, 1.99, t2, "www.validhyperlink.com/image2.png");
		Request r3 = new Request(3, 3, 4.99, t3);

		List<Request> pendingRequests = ed.getPendingRequests(3);

		assertEquals(r1, pendingRequests.get(0));
		assertEquals(r2, pendingRequests.get(1));
		assertEquals(r3, pendingRequests.get(2));
	}

	@Test
	public void getPendingRequestsWorksForEmployeeWithNoPendingRequests() {
		List<Request> pendingRequests = ed.getPendingRequests(2);
		assertEquals(0, pendingRequests.size());
	}

	@Test
	public void getResolvedRequestsWorksForEmployeeWithResolvedRequests() {

		Timestamp t4 = Timestamp.valueOf(LocalDateTime.of(2000, 4, 4, 4, 4));
		Timestamp t5 = Timestamp.valueOf(LocalDateTime.of(2000, 5, 5, 5, 5));
		Timestamp t6 = Timestamp.valueOf(LocalDateTime.of(2000, 6, 6, 6, 6));
		Timestamp t7 = Timestamp.valueOf(LocalDateTime.of(2000, 7, 7, 7, 7));
		
		
		Request r4 = new Request(4, 2, 23.99, t4, "www.validhyperlink.com/image3.png", 1, 2);
		Request r5 = new Request(5, 2, 3.99, t5, "www.validhyperlink.com/image4.png", 2, 1);
		Request r6 = new Request(6, 2, 5.99, t6, "www.validhyperlink.com/image4.png", 1, 2);
		Request r7 = new Request(7, 2, 12.99, t7, null, 1, 1);

		List<Request> resolvedRequests = ed.getResolvedRequests(2);

		assertEquals(r4, resolvedRequests.get(0));
		assertEquals(r5, resolvedRequests.get(1));
		assertEquals(r6, resolvedRequests.get(2));
		assertEquals(r7, resolvedRequests.get(3));
	}

	@Test
	public void getResolvedRequestsWorksForEmployeeWithNoResolvedRequests() {
		List<Request> resolvedRequests = ed.getResolvedRequests(3);
		assertEquals(0, resolvedRequests.size());
	}

	@Test
	public void getEmployeeWorksForValidEmployee() {
		Employee bob1 = new Employee(1, "Bob1", "pass1", "Bob", "Oneson", "1111 Bob Street", "Bobsville", "AZ", "80801",
				"808-808-8081", "bob8081@hotmail.com");
		assertEquals(bob1, ed.getEmployee(1));
	}

	@Test
	public void getEmployeeWorksForValidManager() {
		Manager bob2 = new Manager(2, "Bob2", "pass2", "Tbob", "Twoson", "222 Bob Street", "Bobscity", "CO", "80802",
				"808-808-8082", "bob8082@hotmail.com", 1);
		assertEquals(bob2, ed.getEmployee(2));
	}
	
	@Test
	public void getEmployeeWorksForInvalidEmployee() {
		assertEquals(null, ed.getEmployee(1000));
	}
	
	@Test
	public void submitRequestWorksForValidEmployee() {
		Request req = ed.submitRequest(1, 40, "defvalid.com/imagehyper.png");
		Timestamp requestTime = req.getRequestTime();
		
		List<Request> pendingRequests = ed.getPendingRequests(1);
		List<Request> resolvedRequests = ed.getResolvedRequests(1);
		List<Timestamp> allRequestTimestamps = new ArrayList<Timestamp>();
		
		for (Request r : pendingRequests)
			allRequestTimestamps.add(r.getRequestTime());

		for (Request r : resolvedRequests)
			allRequestTimestamps.add(r.getRequestTime());
		
		assertTrue(allRequestTimestamps.contains(requestTime));
	}
	
	@Test
	public void submitRequestWorksForInvalidEmployee() {
		Request req = ed.submitRequest(100, 40, "defvalid.com/imagehyper.png");
		assertEquals(null, req);
	}
	
	@Test
	public void submitRequestWorksForValidEmployeeNoImage() {
		Request req = ed.submitRequest(6, 94.99);
		Timestamp requestTime = req.getRequestTime();
		
		List<Request> pendingRequests = ed.getPendingRequests(6);
		List<Request> resolvedRequests = ed.getResolvedRequests(6);
		List<Timestamp> allRequestTimestamps = new ArrayList<Timestamp>();
		
		for (Request r : pendingRequests)
			allRequestTimestamps.add(r.getRequestTime());

		for (Request r : resolvedRequests)
			allRequestTimestamps.add(r.getRequestTime());
		
		assertTrue(allRequestTimestamps.contains(requestTime));
		
	}

	@Test
	public void submitRequestWorksForInvalidEmployeeNoImage() {
		Request req = ed.submitRequest(106, 94.99);
		assertEquals(null, req);
	}

	@Test
	public void updateEmployeeUpdatesUsername() {
		ed.updateEmployee(4, EmployeeField.USERNAME, "Bob444");
		assertEquals("Bob444", ed.getEmployee(4).getUsername());
	}
	@After
	public void usernameCleanup() {
		ed.updateEmployee(4, EmployeeField.USERNAME, "Bob4");
	}
	
	@Test
	public void updateEmployeeUpdatesPassword() {
		ed.updateEmployee(4, EmployeeField.PASSWORD, "pass44");
		assertEquals("pass44", ed.getEmployee(4).getPassword());
	}
	@After
	public void passwordCleanup() {
		ed.updateEmployee(4, EmployeeField.PASSWORD, "pass4");
	}
	
	@Test
	public void updateEmployeeUpdatesFirstName() {
		ed.updateEmployee(4, EmployeeField.FIRSTNAME, "Bobf");
		assertEquals("Bobf", ed.getEmployee(4).getFirstName());
		
	}
	@After
	public void firstNameCleanup() {
		ed.updateEmployee(4, EmployeeField.FIRSTNAME, "Fbob");
	}
	
	@Test
	public void updateEmployeeUpdatesLastName() {
		ed.updateEmployee(4, EmployeeField.LASTNAME, "Sonfor");
		assertEquals("Sonfor", ed.getEmployee(4).getLastName());
		
	}
	@After
	public void lastNameCleanup() {
		ed.updateEmployee(4, EmployeeField.LASTNAME, "Fourson");
	}
	
	@Test
	public void updateEmployeeUpdatesAddress() {
		ed.updateEmployee(4, EmployeeField.ADDRESS, "44445 Bob Avenue");
		assertEquals("44445 Bob Avenue", ed.getEmployee(4).getAddress());
		
	}
	@After
	public void addressCleanup() {
		ed.updateEmployee(4, EmployeeField.ADDRESS, "44444 Bob Street");
	}
	
	@Test
	public void updateEmployeeUpdatesCity() {
		ed.updateEmployee(4, EmployeeField.CITY, "Bobston");
		assertEquals("Bobston", ed.getEmployee(4).getCity());
		
	}
	@After
	public void cityCleanup() {
		ed.updateEmployee(4, EmployeeField.CITY, "Bobstopia");
	}
	
	@Test
	public void updateEmployeeUpdatesState() {
		ed.updateEmployee(4, EmployeeField.STATE, "RI");
		assertEquals("RI", ed.getEmployee(4).getState());
		
	}
	@After
	public void stateCleanup() {
		ed.updateEmployee(4, EmployeeField.STATE, "NV");
	}
	
	@Test
	public void updateEmployeeUpdatesZip() {
		ed.updateEmployee(4, EmployeeField.ZIP, "40808");
		assertEquals("40808", ed.getEmployee(4).getZip());
		
	}
	@After
	public void zipCleanup() {
		ed.updateEmployee(4, EmployeeField.ZIP, "80804");
	}
	
	@Test
	public void updateEmployeeUpdatesPhone() {
		ed.updateEmployee(4, EmployeeField.PHONE, "808-808-8048");
		assertEquals("808-808-8048", ed.getEmployee(4).getPhone());
		
	}
	@After
	public void phoneCleanup() {
		ed.updateEmployee(4, EmployeeField.PHONE, "808-808-8084");
	}
	
	@Test
	public void updateEmployeeUpdatesEmail() {
		ed.updateEmployee(4, EmployeeField.EMAIL, "bob8084@gmail.com");
		assertEquals("bob8084@gmail.com", ed.getEmployee(4).getEmail());
		
	}
	@After
	public void emailCleanup() {
		ed.updateEmployee(4, EmployeeField.EMAIL, "bob8084@hotmail.com");
	}
	
}
