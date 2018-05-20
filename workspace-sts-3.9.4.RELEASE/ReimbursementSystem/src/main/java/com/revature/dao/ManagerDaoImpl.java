package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	private String filename = "connection.properties";

	public Employee addEmployee(String username, String password) {
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "INSERT INTO EMPLOYEE (USERNAME, PASSWORD) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeQuery();

			sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next())
				emp = new Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;

	}
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> allEmployees = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			allEmployees = new ArrayList<Employee>();
			
			sql = "SELECT * FROM EMPLOYEE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next())
				allEmployees.add(new Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"), rs.getString("PASSWORD")));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allEmployees;
	}

}
