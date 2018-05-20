package com.revature.main;

import com.revature.dao.*;

public class TestingDriver {

	public static void main(String[] args) {
		ManagerDao md = new ManagerDaoImpl();
		
		System.out.println(md.getAllEmployees());
	}
	

}
