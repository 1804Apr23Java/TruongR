package com.revature.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.beans.Request;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.exception.RequestAlreadyResolvedException;

public class ManagerDaoTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private ManagerDao md = new ManagerDaoImpl();
	private EmployeeDao ed = new EmployeeDaoImpl();

	@Test
	public void getAllPendingRequestsWorks() {

		Timestamp t1 = Timestamp.valueOf(LocalDateTime.of(2000, 1, 1, 1, 1));
		Timestamp t2 = Timestamp.valueOf(LocalDateTime.of(2000, 2, 2, 2, 2));
		Timestamp t3 = Timestamp.valueOf(LocalDateTime.of(2000, 3, 3, 3, 3));

		Request r1 = new Request(1, 3, 54.99, t1, "www.validhyperlink.com/image.png");
		Request r2 = new Request(2, 3, 1.99, t2, "www.validhyperlink.com/image2.png");
		Request r3 = new Request(3, 3, 4.99, t3);
		Request r4 = new Request(8, 1, 4.99, t3);
		Request r5 = new Request(10, 6, 4.99, t3);

		List<Request> pendingRequests = md.getAllPendingRequests();

		assertEquals(r1, pendingRequests.get(0));
		assertEquals(r2, pendingRequests.get(1));
		assertEquals(r3, pendingRequests.get(2));
		assertEquals(r4, pendingRequests.get(3));
		assertEquals(r5, pendingRequests.get(4));
	}

	@Test
	public void getAllResolvedRequestsWorks() {

		Timestamp t4 = Timestamp.valueOf(LocalDateTime.of(2000, 4, 4, 4, 4));
		Timestamp t5 = Timestamp.valueOf(LocalDateTime.of(2000, 5, 5, 5, 5));
		Timestamp t6 = Timestamp.valueOf(LocalDateTime.of(2000, 6, 6, 6, 6));
		Timestamp t7 = Timestamp.valueOf(LocalDateTime.of(2000, 7, 7, 7, 7));

		Request r4 = new Request(4, 2, 23.99, t4, "www.validhyperlink.com/image3.png", 1, 2);
		Request r5 = new Request(5, 2, 3.99, t5, "www.validhyperlink.com/image4.png", 2, 1);
		Request r6 = new Request(6, 2, 5.99, t6, "www.validhyperlink.com/image4.png", 1, 2);
		Request r7 = new Request(7, 2, 12.99, t7, null, 1, 1);
		Request r8 = new Request(9, 6, 3.99, t5, "www.validhyperlink.com/image4.png", 2, 1);

		List<Request> resolvedRequests = md.getAllResolvedRequests();

		assertEquals(r4, resolvedRequests.get(0));
		assertEquals(r5, resolvedRequests.get(1));
		assertEquals(r6, resolvedRequests.get(2));
		assertEquals(r7, resolvedRequests.get(3));
		assertEquals(r8, resolvedRequests.get(4));
	}

	@Test
	public void getAllEmployeeRequestsHandlesEmployeeWithOnlyPendingRequests() {

		Timestamp t1 = Timestamp.valueOf(LocalDateTime.of(2000, 1, 1, 1, 1));
		Timestamp t2 = Timestamp.valueOf(LocalDateTime.of(2000, 2, 2, 2, 2));
		Timestamp t3 = Timestamp.valueOf(LocalDateTime.of(2000, 3, 3, 3, 3));

		Request r1 = new Request(1, 3, 54.99, t1, "www.validhyperlink.com/image.png");
		Request r2 = new Request(2, 3, 1.99, t2, "www.validhyperlink.com/image2.png");
		Request r3 = new Request(3, 3, 4.99, t3);
		
		List<Request> employeeRequests = md.getAllEmployeeRequests(3);

		assertEquals(r1, employeeRequests.get(0));
		assertEquals(r2, employeeRequests.get(1));
		assertEquals(r3, employeeRequests.get(2));
		
	}

	@Test
	public void getAllEmployeeRequestsHandlesEmployeeWithOnlyResolvedRequests() {

		Timestamp t4 = Timestamp.valueOf(LocalDateTime.of(2000, 4, 4, 4, 4));
		Timestamp t5 = Timestamp.valueOf(LocalDateTime.of(2000, 5, 5, 5, 5));
		Timestamp t6 = Timestamp.valueOf(LocalDateTime.of(2000, 6, 6, 6, 6));
		Timestamp t7 = Timestamp.valueOf(LocalDateTime.of(2000, 7, 7, 7, 7));

		Request r4 = new Request(4, 2, 23.99, t4, "www.validhyperlink.com/image3.png", 1, 2);
		Request r5 = new Request(5, 2, 3.99, t5, "www.validhyperlink.com/image4.png", 2, 1);
		Request r6 = new Request(6, 2, 5.99, t6, "www.validhyperlink.com/image4.png", 1, 2);
		Request r7 = new Request(7, 2, 12.99, t7, null, 1, 1);
		
		List<Request> employeeRequests = md.getAllEmployeeRequests(2);

		assertEquals(r4, employeeRequests.get(0));
		assertEquals(r5, employeeRequests.get(1));
		assertEquals(r6, employeeRequests.get(2));
		assertEquals(r7, employeeRequests.get(3));
		
	}

	@Test
	public void getAllEmployeeRequestsHandlesEmployeeWithBothRequests() {
		Timestamp t3 = Timestamp.valueOf(LocalDateTime.of(2000, 3, 3, 3, 3));
		Timestamp t5 = Timestamp.valueOf(LocalDateTime.of(2000, 5, 5, 5, 5));

		Request r8 = new Request(9, 6, 3.99, t5, "www.validhyperlink.com/image4.png", 2, 1);
		Request r5 = new Request(10, 6, 4.99, t3);
		
		List<Request> employeeRequests = md.getAllEmployeeRequests(6);
		
		assertEquals(r8, employeeRequests.get(0));
		assertEquals(r5, employeeRequests.get(1));
	}
	
	@Test
	public void resolveRequestHandlesUnresolvedRequest() {
		Request req = ed.submitRequest(1, 24.49);
		try {
			Request resolvedReq = md.resolveRequest(req.getRequestId(), true, 2);
			assertTrue(md.getAllResolvedRequests().contains(resolvedReq));
		} catch (RequestAlreadyResolvedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void resolveRequestHandlesResolvedRequest() throws RequestAlreadyResolvedException {
		expectedException.expect(RequestAlreadyResolvedException.class);
		md.resolveRequest(6, true, 1);
	}

	@Test
	public void resolveRequestHandlesNonexistentRequest() {
		Request req;
		try {
			req = md.resolveRequest(100, true, 1);
			assertEquals(null, req);
		} catch (RequestAlreadyResolvedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
