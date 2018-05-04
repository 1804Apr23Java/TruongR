package com.revature.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class ConnectionUtil {

	//this is terrible! don't do it this way!!!!!
	public static Connection getConnection() throws SQLException {
		String url = "blah";
		String username = "u5ern8m3";
		String password = "p4ssw0rd";
		return DriverManager.getConnection(url, username, password);
	}
	
	//better
	public static Connection getConnectionFromFile(String filename) throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, username, password);
	}
	
}
