package com.revature.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.*;
import com.revature.domain.*;

public class DaoTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private ManagerDao md = new ManagerDaoImpl();
	
	@Test
	public void getAllEmployeesGetsEverything() {
		Employee bob1 = new Employee(1, "Bob1", "pass1", "Bob", "Oneson", "1111 Bob Street", "Bobsville", "AZ", "80801", "808-808-8081", "bob8081@hotmail.com");
		Employee bob2 = new Employee(2, "Bob2", "pass2", "Tbob", "Twoson", "222 Bob Street", "Bobscity", "CO", "80802", "808-808-8082", "bob8082@hotmail.com");
		Employee bob3 = new Employee(3, "Bob3", "pass3", "Thrbob", "Threeson", "33 Bob Street", "Bobstown", "FL", "80803", "808-808-8083", "bob8083@hotmail.com");
		Employee bob4 = new Employee(4, "Bob4", "pass4", "Fbob", "Fourson", "44444 Bob Street", "Bobstopia", "NV", "80804", "808-808-8084", "bob8084@hotmail.com");
		Employee bob5 = new Employee(5, "Bob5", "pass5", "Fibob", "Fiveson", "5 Bob Street", "Bobscitadel", "OR", "80805", "808-808-8085", "bob8085@hotmail.com");
		Employee bob6 = new Employee(6, "Bob6", "pass6", "Sbob", "Sixson", "6666 Bob Street", "Bobsterrace", "MD", "80806", "808-808-8086", "bob8086@hotmail.com");
		
		List<Employee> allEmployees = md.getAllEmployees();
		
		assertEquals(bob1, allEmployees.get(0));
		assertEquals(bob2, allEmployees.get(1));
		assertEquals(bob3, allEmployees.get(2));
		assertEquals(bob4, allEmployees.get(3));
		assertEquals(bob5, allEmployees.get(4));
		assertEquals(bob6, allEmployees.get(5));
		
	}
}
