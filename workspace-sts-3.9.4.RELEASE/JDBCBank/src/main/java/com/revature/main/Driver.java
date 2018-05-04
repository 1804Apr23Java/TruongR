package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		try {
			Connection c = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println("success");
		} catch (SQLException e) {
			System.out.println("phail");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("phail");
			e.printStackTrace();
			
		}
	}
	
}
