package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.*;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	private String filename = "connection.properties";

	/*
	 * public Employee addEmployee(String username, String password, String
	 * firstName, String lastName, String address, String city, String state, String
	 * zip, String phone, String email) { PreparedStatement pstmt = null; String sql
	 * = null; ResultSet rs = null; Employee emp = null;
	 * 
	 * try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
	 * 
	 * sql =
	 * "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) "
	 * + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)"; pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, username); pstmt.setString(2, password);
	 * pstmt.setString(3, firstName); pstmt.setString(4, lastName);
	 * pstmt.setString(5, address); pstmt.setString(6, city); pstmt.setString(7,
	 * state); pstmt.setString(8, zip); pstmt.setString(9, phone);
	 * pstmt.setString(10, email); pstmt.executeQuery();
	 * 
	 * sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?AND PASSWORD = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, username); pstmt.setString(2,
	 * password); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) emp = new Employee(rs.getInt("EMPLOYEEID"),
	 * rs.getString("USERNAME"), rs.getString("PASSWORD"),
	 * rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"),
	 * rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
	 * rs.getString("PHONE"), rs.getString("EMAIL"));
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * return emp;
	 * 
	 * }
	 */

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
				allEmployees.add(new Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"),
						rs.getString("PASSWORD"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
						rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
						rs.getString("PHONE"), rs.getString("EMAIL")));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allEmployees;
	}

	/*
	 * @Override public Employee getEmployee(String username) {
	 * 
	 * Employee emp = null; PreparedStatement pstmt = null; String sql = null;
	 * ResultSet rs = null, rs2 = null;
	 * 
	 * try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
	 * 
	 * sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, username); rs =
	 * pstmt.executeQuery();
	 * 
	 * if (rs.next()) { int employeeId = rs.getInt("EMPLOYEEID"); sql =
	 * "SELECT * FROM MANAGER WHERE EMPLOYEEID = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, employeeId); rs2 =
	 * pstmt.executeQuery(); if (rs2.next()) emp = new
	 * Manager(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"),
	 * rs.getString("PASSWORD"), rs.getString("FIRSTNAME"),
	 * rs.getString("LASTNAME"), rs.getString("ADDRESS"), rs.getString("CITY"),
	 * rs.getString("STATE"), rs.getString("ZIP"), rs.getString("PHONE"),
	 * rs.getString("EMAIL"), rs2.getInt("MANAGERID")); else emp = new
	 * Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"),
	 * rs.getString("PASSWORD"), rs.getString("FIRSTNAME"),
	 * rs.getString("LASTNAME"), rs.getString("ADDRESS"), rs.getString("CITY"),
	 * rs.getString("STATE"), rs.getString("ZIP"), rs.getString("PHONE"),
	 * rs.getString("EMAIL")); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * return emp; }
	 */

}
