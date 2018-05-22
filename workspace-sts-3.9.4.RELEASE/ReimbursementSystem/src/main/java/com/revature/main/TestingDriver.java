package com.revature.main;

import com.revature.dao.*;

public class TestingDriver {

	public static void main(String[] args) {
		EmployeeDao ed = new EmployeeDaoImpl();
		System.out.println(ed.getEmployee(2));
	}
	

}
