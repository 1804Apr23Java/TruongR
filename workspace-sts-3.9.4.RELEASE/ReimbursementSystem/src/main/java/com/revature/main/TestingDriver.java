package com.revature.main;

import com.revature.dao.*;

public class TestingDriver {

	public static void main(String[] args) {
		ManagerDao md = new ManagerDaoImpl();
		md.addEmployee("bob7", "pass7", "Sebob", "Sevenson", "8888 Bob Street", "Bobshore", "SC", "80807", "808-808-8087", "bob8087@hotmail.com");
		System.out.println(md.getAllEmployees());
	}
	

}
